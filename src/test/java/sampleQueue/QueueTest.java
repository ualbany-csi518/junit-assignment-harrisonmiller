package sampleQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class QueueTest {

	/**
	 * Tests for Queue.
	 */

	private static final String SOME_ITEM = "some-content";
	private Queue<String> q;

	@Test
	@DisplayName("is instantiated with new Queue()")
	void isInstantiatedWithNew() {
		new Queue<>();
	}
	
	@Test
	@DisplayName("is instantiated with new Queue(maxLength) with length")
	void isInstantiatedWithNewWithMaxLength() {
		new Queue<>(10);
	}
	
	@Nested
	@DisplayName("when new")
	class WhenNew {

		@BeforeEach
		void createNewQueue() {
			q = new Queue<>();
		}

		@Test
		@DisplayName("is empty")
		void isEmpty() {
			assertTrue(q.isEmpty());
		}
		
		@Test
		@DisplayName("length should be zero")
		void lengthShouldReturnZero() {
			assertEquals(0, q.length());
		}

		@Test
		@DisplayName("throws NoSuchElementException when dequeued")
		void throwsExceptionWhenDequeued() {
			Assertions.assertThrows(NoSuchElementException.class, q::dequeue);
		}

		@Test
		@DisplayName("throws NoSuchElementException when peeked")
		void throwsExceptionWhenPeeked() {
			assertThrows(NoSuchElementException.class, q::peek);
		}
		
		@Test
		@DisplayName("removeAll should do nothing when empty")
		void removeAllWhenEmpty() {
			q.removeAll();
		}
		
		@Nested
		@DisplayName("after enqueuing an element")
		class AfterEnqueuing {

			String anElement = "an element";

			@BeforeEach
			void enqueueAnElement() {
				q.enqueue(anElement);
			}

			@Test
			@DisplayName("it is no longer empty")
			void isNotEmpty() {
				assertFalse(q.isEmpty());
			}
			
			@Test
			@DisplayName("length should not be zero")
			void lengthShouldReturnNotZero() {
				assertFalse(q.length() == 0);
			}

			@Test
			@DisplayName("returns the element when dequeued and is empty")
			void returnElementWhenDequeued() {
				assertEquals(anElement, q.dequeue());
				assertTrue(q.isEmpty());
			}

			@Test
			@DisplayName("returns the element when peeked but remains not empty")
			void returnElementWhenPeeked() {
				assertEquals(anElement, q.peek());
				assertFalse(q.isEmpty());
			}
			
			@Test
			@DisplayName("removeAll should remove all elements")
			void removeAllShouldRemoveAllElements() {
				q.removeAll();
				assertTrue(q.isEmpty());
			}
		}
	}
	
	@Nested
	@DisplayName("when full")
	class WhenFull {
		
		String anElement = "test";
		int maxLength = 5;

		@BeforeEach
		void createNewQueue() {
			q = new Queue<String>(maxLength);
			q.enqueue("1");
			q.enqueue("2");
			q.enqueue("3");
			q.enqueue("4");
			q.enqueue("5");
		}

		@Test
		@DisplayName("is empty")
		void isEmpty() {
			assertFalse(q.isEmpty());
		}
		
		@Test
		@DisplayName("length should not be zero")
		void lengthShouldNotReturnZero() {
			assertFalse(q.length() == 0);
		}

		@Test
		@Disabled
		@DisplayName("Enqueue should not work when full")
		void enqueueWhenFullShouldNotSurpassMaxLength() {
			assertTrue(1==2);
		}

		@Test
		@DisplayName("returns element when peeked")
		void peekReturnsFirstElement() {
			assertTrue(q.peek().equals("1"));
		}
		
		@Test
		@DisplayName("removeAll should empty the queue")
		void removeAllWhenEmpty() {
			q.removeAll();
			assertTrue(q.isEmpty());
		}
		
	}

	@Test
	@BeforeEach
	void init() {
		this.q = new Queue<String>();
	}

	@Test
	@DisplayName("Verify Queue isEmpty when queue is initialized")
	void isEmptyShouldGiveTrueOnQueueInit() {
		assertTrue(q.isEmpty());
	}
	
	@Test
	@DisplayName("Verify Enqueue works with more than one element in the queue")
	void enqueueTestIfNotEmptyOrFull() {
		q.enqueue("1");
		q.enqueue("2");
	}
	
	@Test
	@DisplayName("Verify dequeue works with one element left after")
	void dequeueShouldSetLastToNullIfOneElement() {
		q.enqueue("1");
		q.enqueue("2");
		q.dequeue();
	}	

	//Example of Wrong Test! 
	@Test
	@Disabled
	@DisplayName("Verify Queue isEmpty returns false when queue is not empty")
	void isEmptyShouldGiveFalseWhenQueueIsNotEmpty() {
		this.q.enqueue(SOME_ITEM);
		assertFalse(q.isEmpty());
	}

	
}