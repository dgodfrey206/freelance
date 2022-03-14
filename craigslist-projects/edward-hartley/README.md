Edward (3/8/2022 8:15 PM):

> From the `MAIN()`, I want to call a function and pass an integer array to the function. Let say the function is: int cal(array);

> Somewhere in the middle of the function before I return, I want to call back on `MAIN()` to send me another integer array with `int cal(array)` again.

> I need an example of how to do this properly. Can you help. I am using C.

Me (3/8/2022 8:27 PM): 

> Hi,

> Could you tell me a bit more about what you're trying to do. What is the purpose of the `cal()` function and why do you need a second array?

Edward (3/8/2022 8:31 PM):

> I just want to see how to continually call for new "int arrays" from the `main()` without exiting the cal function. 

> Something like this. But not exactly.

>     #include <stdio.h>
> 
>     int cal()  {
>       for (i = 1; i < 8; i++) {
>         // SOME CODE
>         printf("\n");
>         int ret = main();
>       }
>     }
> 
>     int main() { 
>       int array[8] = {2,3,4,5,6,7,8};
>       int ret = cal(array);
>       return 0;
>     }

Me (3/8/2022 8:41 PM):

> I believe you would run   cal()    in a separate thread, then inside `cal()` you would use some kind of routine to "wait" for the main thread to produce a new array. This is pretty unconventional though, and probably not what you need to do.

Edward (3/8/2022 8:43 PM):

> I am looking for an example of how to get something like that done properly.

Me (3/8/2022 8:52 PM):

> Sure. I can send you an example

> ...

Me (3/8/2022 10:52 PM): 

> Here is your program. I feel I should explain how it works.

If I understood your request, you wanted to pass in an array to the `cal()` function, and then essentially request new arrays from `main()` later in the function. I was able to do this by putting `cal()` in its own thread and loop while waiting for a signal from main to be emitted (which I catch through a condition variable). Once the signal is caught I can access the new array which gets stored in a global variable.

The new arrays are stored in the "data" struct. The `changeArray()` method generates a new array into 'data', and then the main method signals out that the array has been changed with a condition variable. The `cal()` function uses that condition variable to catch that change, prints out the new array, and then signals back to main that it's finished printing. 

If my interpretation of your request was off, or if you need anything else just let me know. I hope this helps.