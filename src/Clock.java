

public class Clock {

	private int ticksPerSec;
	private long targetTime;
	
	
	public Clock(int ticksPerSec) {
		this.ticksPerSec = ticksPerSec;
		targetTime = System.currentTimeMillis() + (long)Math.pow(10,3)/ticksPerSec;
	}
	
	public void waitUntilGameTick() {
		
		
			//System.out.println(targetTime - System.currentTimeMillis());
		long time = targetTime - System.currentTimeMillis();
		//System.out.println(time);
		if (time > 0) {
			try {
			Thread.sleep(time);
			}catch (Exception e) {}
		}
		
		
		targetTime = System.currentTimeMillis() + (long)Math.pow(10,3)/ticksPerSec;
	}
	
	/*
    private int ticksPerSec;
    private long gameTickTarget;

    private int fps;
    private int fpsCounter;
    private long fpsTickTarget;


    public Clock(int ticksPerSec){
        this.ticksPerSec = ticksPerSec;
        gameTickTarget = System.nanoTime() - (long)((1.0/ticksPerSec) * 1000000000L);
        fpsTickTarget = System.nanoTime();
    }

    public boolean isGameTick(){
        if (System.nanoTime() - gameTickTarget >= 0){
            gameTickTarget += (long)((1.0/ticksPerSec) * 1000000000L);
            //System.out.println();
            return true;
        }
        //System.out.println(gameTickTarget);
        return false;
    }

    public int getFps(){
        return fps;
    }

    public void addToFPS(){
        if (System.nanoTime() - fpsTickTarget >= 0){
            fpsTickTarget += 1000000000L;
            fps = fpsCounter;
            fpsCounter = 0;
        }
        fpsCounter++;
    }

    public void waitUntilGameTick(){
        long length = gameTickTarget - System.nanoTime();
        //System.out.println(gameTickTarget + ", " + System.nanoTime());
        //System.out.println(length);
        try{
            Thread.sleep((length / 1000000));
            //System.out.println("o");
        }catch (Exception e){
            //System.out.println(e.getMessage());
        }
    }
    */
}
