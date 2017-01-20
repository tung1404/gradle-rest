package vn.tungnt.service.resources;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.continuations.Continuation;
import org.apache.cxf.continuations.ContinuationProvider;
import org.apache.cxf.jaxrs.ext.MessageContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

//http://www.vogella.com/tutorials/REST/article.html
@Component
@Path("/hello")
public class Hello {

	@Context
	private MessageContext context;

	@Autowired
	private TaskExecutor taskExecutor;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello() throws InterruptedException, ExecutionException {
		// http://blog.clempinch.com/async-rest-cxf-and-the-continuations-api/
		// get the Continuation API object
		ContinuationProvider continuationProvider = (ContinuationProvider) context.get(ContinuationProvider.class.getName());
		final Continuation continuation = continuationProvider.getContinuation();
		synchronized (continuation) {
			if(continuation.isNew()) {
				// it means this is a new request
				// we execute a task asynchronously
				FutureTask<String> futureResponse = new FutureTask<String>(new Callable<String>() {
					
					@Override
					public String call() throws Exception {
						// execute the asynchronous job here
						Thread.sleep(5000);
						// resume the request
						continuation.resume();
						return "Hello Jersey";
					}
				});
				taskExecutor.execute(futureResponse);
				// we store the future task in the continuation object for future retrieval
				continuation.setObject(futureResponse);
				// and we suspend the request
				continuation.suspend(6000);
				// this will not be returned to the client
				return null;
			} else {
				// it means the request has been resumed or that a timeout occurred
				@SuppressWarnings("unchecked")
				FutureTask<String> futureTask = (FutureTask<String>) continuation.getObject();
				return futureTask.get();
			}
		}
	}

	@GET
	@Produces(MediaType.TEXT_XML)
	public String sayXMLHello() {
		return "<?xml version=\"1.0\"?>" + "<hello> Hello Jersey" + "</hello>";
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayHtmlHello() {
		return "<html> " + "<title>" + "Hello Jersey" + "</title>" + "<body><h1>" + "Hello Jersey" + "</body></h1>"
				+ "</html> ";
	}

}
