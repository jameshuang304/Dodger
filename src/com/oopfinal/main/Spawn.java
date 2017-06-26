package com.oopfinal.main;

import java.util.Random;

public class Spawn
{

	private Handler handler;
	private HUD hud;
	private Game game;
	private Random r = new Random();
	private int scoreKeep = 0;
	private Boolean extreme = false;

	public Spawn(Handler handler, HUD hud, Game game)
	{
		this.handler = handler;
		this.hud = hud;
		this.game = game;
	}

	public void tick()
	{
		scoreKeep++;
		if (scoreKeep >= 250)
		{
			scoreKeep = 0;
			hud.setLevel(hud.getLevel() + 1);

			if (game.difficulty == 0)
			{
				if (hud.getLevel() == 2)
				{
					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50),
							ID.BasicEnemy, handler));
				}
				else if (hud.getLevel() == 3)
				{
					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50),
							ID.BasicEnemy, handler));
				}
				else if (hud.getLevel() == 4)
				{
					handler.addObject(new MenuParticle(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50),
							ID.FastEnemy, handler));
				}
				else if (hud.getLevel() == 5)
				{
					handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50),
							ID.SmartEnemy, handler));
				}
				else if (hud.getLevel() == 6)
				{
					handler.addObject(new MenuParticle(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50),
							ID.FastEnemy, handler));
				}
				else if (hud.getLevel() == 7)
				{
					handler.addObject(new MenuParticle(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50),
							ID.FastEnemy, handler));
				}
				else if (hud.getLevel() == 10)
				{
					handler.clearEnemys();
					handler.addObject(new EnemyBoss((Game.WIDTH / 2) - 48, -120, ID.EnemyBoss, handler));
				}
			}
			else if (game.difficulty == 1)
			{
				if (hud.getLevel() == 2)
				{
					handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50),
							ID.BasicEnemy, handler));
				}
				else if (hud.getLevel() == 3)
				{
					handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50),
							ID.BasicEnemy, handler));
				}
				else if (hud.getLevel() == 4)
				{
					handler.addObject(new MenuParticle(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50),
							ID.FastEnemy, handler));
				}
				else if (hud.getLevel() == 5)
				{
					handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50),
							ID.SmartEnemy, handler));
				}
				else if (hud.getLevel() == 6)
				{
					handler.addObject(new MenuParticle(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50),
							ID.FastEnemy, handler));
				}
				else if (hud.getLevel() == 7)
				{
					handler.addObject(new MenuParticle(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50),
							ID.FastEnemy, handler));
				}
				else if (hud.getLevel() == 10)
				{
					handler.clearEnemys();
					handler.addObject(new EnemyBoss((Game.WIDTH / 2) - 48, -120, ID.EnemyBoss, handler));
				}

			}
			else if (game.difficulty == 100)
			{
				if (!extreme)
				{
					handler.clearEnemys();
					extreme = true;
					hud.setLevel(1);
					extremeMod();
				}
				else if (hud.getLevel() == 3 ||
						 hud.getLevel() == 5 ||
						 hud.getLevel() == 7)
				{
					extremeMod();
				}
				else if (hud.getLevel() == 10)
				{
					extremeMod();
					extremeMod();
					extremeMod();
				}
				
			}
		}
	}
	
	
	
	private void extremeMod()
	{
		for(int i = 0 ; i < 5 ; i++)
		{
			handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50),
					ID.BasicEnemy, handler));
		}
		
		for(int i = 0 ; i < 5 ; i++)
		{
			handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50),
					ID.SmartEnemy, handler));
		}
		for(int i = 0 ; i < 8 ; i++)
		{
			handler.addObject(new MenuParticle(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50),
					ID.FastEnemy, handler));
		}
	}
}