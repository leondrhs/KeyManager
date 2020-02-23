# KeyManager
A useful KeyManager class written in Java. Please feel free to use it in your games or whatever.

Just add the KeyManager update() function to your main Thread and you're good to go!

And don't forget to register the KeyListener to your Panel.

Simple example:

    -> Player.java
      
    if(KeyManager.getKey('W') > KeyManager.FREE) //If 'W' is PRESSED, DOWN or RELEASED
    {
        y -= speed;
    }

All key states can be found in the KeyManager class:

    -> KeyManager.java

    ...
    public static final int FREE = 0;
    public static final int PRESSED = 1;
    public static final int DOWN = 2;
    public static final int RELEASED = 3;
    ...
