/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

/**
 * Group 22
 * @author bdg7335 + mjh6326
 */
public class Break extends Thread {
    private int sleep;
    
    public Break(int delay)
    {
        this.sleep = delay;
        run();
    }
    
    public void setSleep(int sleep)
    {
        this.sleep = sleep;
        run();
    }
    
    @Override
    public void run()
    {
        try
        {
            Thread.sleep(this.sleep);
        }
        catch (InterruptedException interruptedException)
        {
            System.err.println(interruptedException);
        }
    }
}
