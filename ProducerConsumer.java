import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProducerConsumer {
   public static class prodConsProblem 
    { 
LinkedList<Integer> LIST = new LinkedList<>(); int capacity = 4; 
// To Produce data 
public void producer() throws InterruptedException { 
        int data = 0; 
        while (true) { 
        synchronized (this) { 
        // wait if buffer is full
        while (LIST.size()==capacity) 
            wait(); 
// to insert data to buffer
        LIST.add(data++);
System.out.println("Producer Has    Produced: " + data); 
// notifing consumer thread for availability of data
        notify();  Thread.sleep(1000);}} 
    } 
//Consume data 
public void consumer() throws InterruptedException { 
        while (true) { 
        synchronized (this) { 
        //wait if no data in buffer
        while (LIST.size()==0) 
        wait();

//consume data from list
int consumedData = LIST.removeFirst(); 
System.out.println("Consumer has Consumed: " + consumedData); 
// notify producer to produce
    notify();  Thread.sleep(1000);}} 
    }} 

 public static void main(String[] args) {
 prodConsProblem obj=new prodConsProblem();
// Creating producer thread by simple override method
Thread prodThread = new Thread(new Runnable(){@Override
public void run(){ 
try {obj.producer();}
 catch (InterruptedException ex) {
Logger.getLogger(ProducerConsumer.class.getName()).log(Level.SEVERE, null, ex);}} 
        }); 
        //Starting producer thread
        prodThread.start(); 
    	// Creating consumer thread using lambda function to override run method
Thread consThread = new Thread(new Runnable(){
  @Override
public void run(){ 
try {obj.consumer();} 
catch (InterruptedException ex) {
Logger.getLogger(ProducerConsumer.class.getName()).log(Level.SEVERE, null, ex);}}}
); 
//Starting consumer thread
consThread.start(); }}
