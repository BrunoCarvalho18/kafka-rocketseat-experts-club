package com.brunocarvalho.bookstoreeventsproducer.controller;

import java.util.concurrent.ExecutionException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.brunocarvalho.bookstoreeventsproducer.domain.BookStoreEvent;
import com.brunocarvalho.bookstoreeventsproducer.domain.BookStoreEventType;
import com.brunocarvalho.bookstoreeventsproducer.producer.BookStoreEventProducer;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class BookStoreEventController {
	
	@Autowired
	BookStoreEventProducer bookStoreEventProducer;
	
	@PostMapping("/v1/bookstore-events")
	public ResponseEntity<BookStoreEvent> postBookStoreEvent(@RequestBody @Valid BookStoreEvent bookStoreEvent) throws JsonProcessingException{
		
		bookStoreEvent.setBookStoreEventType(BookStoreEventType.NEW);
		
		bookStoreEventProducer.senBookStoreEvent_Approach2(bookStoreEvent);
	    
		return ResponseEntity.status(HttpStatus.CREATED).body(bookStoreEvent);
		
	}
	
	 @PutMapping("/v1/bookstore-event")
	    public ResponseEntity<?> putLibraryEvent(@RequestBody @Valid BookStoreEvent bookStoreEvent) throws JsonProcessingException, ExecutionException, InterruptedException {


	        if(bookStoreEvent.getBookStoreEventId() == null){
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Eai parca passa o bookStoreEventId aeeee senao nao rola");
	        }

	        bookStoreEvent.setBookStoreEventType(BookStoreEventType.UPDATE);
	        bookStoreEventProducer.senBookStoreEvent_Approach2(bookStoreEvent);
	        return ResponseEntity.status(HttpStatus.OK).body(bookStoreEvent);
	    }

}
