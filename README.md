# android-retrofit-example
" IllegalStateException: Expected BEGIN_OBJECT but was BEGIN_ARRAY at line 1 column 2 path $ "



What do you do when you get this error using GSON and Retrofit library ? 



Hint : you are wrong about the object you are parsing.

Ok, it wasn't that complicated to guess...



But how do we solve this ? 



"Expected BEGIN_OBJECT" is the object from the Call<> returned in your service interface.



Most of the chances whilst parsing you want a collection of objects. 

But as parsers we like to save some lines and create an object being a collection of  a POJO . 

And by mistake, happens that we ask for a collection of this object, being already a collection and PUF !

We required an object (collection) but we've reached the array we want to parse as our POJO and the error shows up !
