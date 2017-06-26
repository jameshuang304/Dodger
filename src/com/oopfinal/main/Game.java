package com.oopfinal.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;


/**
 * This class is the main game class that implement runnable to 
 * create main loop.
 * @author powei
 * @version 1.00
 */

public class Game extends Canvas implements Runnable
{
	private static final long serialVersionUID = 1550691097823471818L;

	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;

	private Thread thread;
	private boolean running = false;

	public static boolean paused = false;
	public int difficulty = 0;
	// 0 = normal
	// 1 = hard

	private Random r;
	private Handler handler;
	private HUD hud;
	private Spawn spawner;
	private Menu menu;

	public enum STATE
	{
		Menu, Select, Help, Game, End
	};

	public static STATE gameState = STATE.Menu;
	
	/**
	 * This is the ctor that init every game need.
	 */
	public Game()
	{
		handler = new Handler();
		hud = new HUD();
		menu = new Menu(this, handler, hud);
		this.addKeyListener(new KeyInput(handler, this));
		this.addMouseListener(menu);

		AudioPlayer.load();

		AudioPlayer.getMusic("music").loop();

		new Window(WIDTH, HEIGHT, "OOP project", this);

		spawner = new Spawn(handler, hud, this);
		r = new Random();

		if (gameState == STATE.Game)
		{
			handler.addObject(new Player(WIDTH / 2 - 32, HEIGHT / 2 - 32, ID.Player, handler));
			handler.addObject(
					new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
		}
		else
		{
			for (int i = 0; i < 20; i++)
			{
				handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler));
			}
		}

	}

	public synchronized void start()
	{
		thread = new Thread(this);
		thread.start();
		running = true;

	}

	public synchronized void stop()
	{
		try
		{
			thread.join();
			running = false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	@Override
	public void run()
	{
		this.requestFocus(); // don't need to click on screen to gain control of
								// keyboard input
		long lastTime = System.nanoTime();
		double numOfTicks = 60.0;
		double interval = 1000000000 / numOfTicks;
		double delta = 0;
		while (running)
		{
			long now = System.nanoTime();
			delta += (now - lastTime) / interval;
			lastTime = now;
			while (delta >= 1)
			{
				tick();
				delta--;
			}
			if (running)
				render();

		}
		stop();
	}

	private void tick()
	{
		if (gameState == STATE.Game)
		{

			if (!paused)
			{
				hud.tick();
				spawner.tick();
				handler.tick();

				if (HUD.HEALTH <= 0)
				{
					HUD.HEALTH = 100;
					gameState = STATE.End;
					handler.clearEnemys();
					for (int i = 0; i < 20; i++)
					{
						handler.addObject(
								new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler));
					}

				}
			}
		}
		else if (gameState == STATE.Menu || gameState == STATE.End || gameState == STATE.Select)
		{
			menu.tick();
			handler.tick();
		}

	}

	private void render()
	{
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null)
		{
			this.createBufferStrategy(2);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		handler.render(g);

		if (paused)
		{
			g.setColor(Color.white);
			g.drawString("PAUSED", 100, 100);
		}

		if (gameState == STATE.Game)
		{
			hud.render(g);
		}
		else if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End
				|| gameState == STATE.Select)
		{
			menu.render(g);
		}
		g.dispose();
		bs.show();
	}

	/**
	 * restrict area
	 */
	public static float clamp(float var, float min, float max)
	{
		if (var >= max)
			return var = max;
		else if (var < min)
			return var = min;
		else
			return var;
	}

	public static void main(String args[])
	{
		new Game();
	}
}
