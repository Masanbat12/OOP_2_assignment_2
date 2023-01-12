# OOP_2_assignment_2
## Part_A
This is a Java project that demonstrates the use of threads and thread pools in reading the number of lines in a set of text files.

### Features:
#### createTextFiles(int n, int seed, int bound):
This method creates n number of text files, each containing a random number of lines (from 0 to bound) and returns an array of strings representing the file names.

#### getNumOfLines(String[] fileNames): 
This method reads all lines in the text files specified in the fileNames array and returns the total number of lines.

#### getNumOfLinesThreads(String[] fileNames): 
This method creates a separate thread for each file in the fileNames array, and returns the total number of lines in all the files.

#### getNumOfLinesThreadPool(String[] fileNames):
This method creates a fixed thread pool with the same number of threads as the number of files in the fileNames array and returns the total number of lines in all the files.

#### Output:
The program will output the time taken for each of the methods getNumOfLines, getNumOfLinesThreads, and getNumOfLinesThreadPool to complete their execution.


ThreadPool is the most efficient of all when dealing with a large number of files.
In cases where the normal Thread method was more efficient, especially in cases where we were working on a lower number of files.
![UML](https://user-images.githubusercontent.com/93978448/212144659-ca4333b6-b7e4-4589-9b79-e25e55c3d72f.JPG)

## Part_B
This project is about creating management of task's, giving an asynchronous task with priorityType and
a type of ThreadPool that is supporting tasks priority, it's an extands of the CustomExcutor.
We were given one class to TaskType that show the way of how to work,
and we got a Test class to check our Code and which method to implement.
The task Objects type are of threads, that we implemented in the 2 class that we were required to write.
The 2 class's we created are Task and CustoExcutor.
we created a third class that is called fType that are actully an Adapter for 2 interfaces : Callable and Future.
They would act as a connector between two incompatible interfaces, otherwise they cannot be connected directly.
#### Callable:
A Callable interface is a feature in the Java programming language that allows a class to define a method that can be called as if it were a function.
The Callable interface is similar to the Runnable interface, but it can return a value and throw a checked exception.
A class that implements the Callable interface must define a single method called call(), 
which takes no arguments and returns a value or throws an exception.
#### Future:
A Future interface is a feature in the Java programming language that represents the result of an asynchronous computation.
It allows you to submit a task for execution, and then retrieve the result of the task at a later time.

### Classes we implemented:
#### Task:
This class is an operation that can be call asynchronously (Callable interface) and can return a value of some type (var),
so we define it as a type Generic return.
It's not necessary for the operation to work and in case of failure, an exception will be thrown.
We also implemented Factory method that is called creatTask, the first gets 2 types Callable and TaskType,
the second one is getting only Callable.
#### TaskType:
This class is an enum, it describes the task type :COMPUTATIONAL(1),IO(2),OTHER(3),
and its priority based on the value The number of the task type.
#### CustomExcutor:
This class is  extends ThreadPoolExecutor interface, which is providing a way
to execute Runnable, Callable and Future tasks asynchronously.
#### Adapter(fType class):
An adapter interface, also known as an adapter pattern, is a design pattern used to allow classes with incompatible interfaces to work together.
In object-oriented programming, an adapter interface is typically used to adapt one interface to another, allowing classes that implement different interfaces to interact with one another.
fType extends the FutureTask class and implements the Comparable interface.
By this class we can have the Object <T> to be used in priority queue.
  
![WhatsApp Image 2023-01-12 at 18 49 10](https://user-images.githubusercontent.com/93978448/212145428-a52e1334-5000-416b-851b-97a5a9c99be8.jpeg)

