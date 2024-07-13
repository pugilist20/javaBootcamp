**Exercise 00 – Egg, Hen... or Human?**
The truth is born in a dispute—let's assume that each thread provides its own answer. The thread that has the last word is right.
You need to implement the operation of two threads. Each of them must display its answer a few times, for example, 50
In this case, the egg thread wins. However, the program also contains main thread. Inside the thread, public static void main(String args[]) method is executed. We need this thread to display all its responses at the end of program execution.
It means that the program outputs Human message 50 times, which main thread prints.

**Exercise 01 – Egg, Hen, Egg, Hen...**
Let's orchestrate the argument. Now, each thread can provide its answer only after another thread has done so.

**Exercise 02 – Real Multithreading**
Try to use multithreading for its intended purpose: distribute computations across the program.
Let's assume there is an array of integer values. Your goal is to calculate the sum of array elements using several "summing" threads. Each thread computes a certain section inside the array. The number of elements in each section is constant, except for the last one (its size can differ upward or downward).
The array shall be randomly generated each time. Array length and the number of threads are passed as command-line arguments.
To make sure the program operates correctly, we should start by calculating the sum of array elements using a standard method.
Maximum number of array elements is 2,000,000. Maximum number of threads is no greater than current number of array elements. Maximum modulo value of each array element is 1,000. All data is guaranteed to be valid.

**Exercise 03 – Too Many Threads...**
Let's assume that we need to download a list of files from a network. Some files are downloaded faster, while others are slower.
To implement this functionality, we can obviously use multithreaded downloading where each thread loads a specific file. But what should we do if there are too many files? A large number of threads cannot be run at the same time. Therefore, many of them will be waiting.
In addition, we should bear in mind that continuously creating and completing threads is a very costly operation we should avoid. It makes more sense to start N threads at once and, when either of them finishes downloading the file, it can take on the next file in the queue.
We need to create files_urls.txt file (file name shall be explicitly specified in program code) where you specify a list of URLs of files to be downloaded
