package com.oopfinal.main;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

/**
 * Created by jameshuang304 on 2017/6/24.
 */
public class Menu extends MouseAdapter
{

	private Game game;
	private Handler handler;
	private HUD hud;
	private Random r = new Random();

	public Menu(Game game, Handler handler, HUD hud)
	{
		this.game = game;
		this.hud = hud;
		this.handler = handler;
	}

	public void mousePressed(MouseEvent e)
	{
		int mx = e.getX();
		int my = e.getY();

		if (game.gameState == Game.STATE.Menu)
		{
			if (mouseOver(mx, my, 210, 150, 200, 64))
			{
				game.gameState = Game.STATE.Select;
				AudioPlayer.getSound("menu_sound").play();
				return;
			}
			// help button
			if (mouseOver(mx, my, 210, 250, 200, 64))
			{
				game.gameState = Game.STATE.Help;
				AudioPlayer.getSound("menu_sound").play();
			}
			// quit button
			if (mouseOver(mx, my, 210, 350, 200, 64))
			{
				System.exit(1);
			}
		}

		if (game.gameState == Game.STATE.Select)
		{
			// normal button
			if (mouseOver(mx, my, 210, 150, 200, 64))
			{
				game.gameState = Game.STATE.Game;
				handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
				handler.clearEnemys();
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy,
						handler));

				game.difficulty = 0;

				AudioPlayer.getSound("menu_sound").play();
			}
			// hard button
			if (mouseOver(mx, my, 210, 250, 200, 64))
			{
				game.gameState = Game.STATE.Game;
				handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
				handler.clearEnemys();
				handler.addObject(
						new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));

				game.difficulty = 1;

				AudioPlayer.getSound("menu_sound").play();
			}
			// back button
			if (mouseOver(mx, my, 210, 350, 200, 64))
			{
				game.gameState = Game.STATE.Menu;
				AudioPlayer.getSound("menu_sound").play();
				return;
			}
		}

		// back button for help
		if (game.gameState == Game.STATE.Help)
		{
			if (mouseOver(mx, my, 210, 350, 200, 64))
			{
				game.gameState = Game.STATE.Menu;
				AudioPlayer.getSound("menu_sound").play();
				return;
			}
		}

		// try again button for end
		if (game.gameState == Game.STATE.End)
		{
			if (mouseOver(mx, my, 210, 350, 200, 64))
			{
				game.gameState = Game.STATE.Menu;
				hud.setLevel(1);
				hud.setScore(0);
				AudioPlayer.getSound("menu_sound").play();
			}
		}

	}

	public void mouseReleased(MouseEvent e)
	{

	}

	private boolean mouseOver(int mx, int my, int x, int y, int width, int height)
	{
		if (mx > x && mx < x + width)
		{
			if (my > y && my < y + height)
			{
				return true;
			}
			else
				return false;
		}
		else
			return false;
	}

	public void tick()
	{

	}

	public void render(Graphics g)
	{
		if (game.gameState == Game.STATE.Menu)
		{
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);

			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Dodger", 220, 70);

			g.setFont(fnt2);
			g.drawRect(210, 150, 200, 64);
			g.drawString("Play", 280, 190);

			g.drawRect(210, 250, 200, 64);
			g.drawString("Help", 280, 290);

			g.drawRect(210, 350, 200, 64);
			g.drawString("Quit", 280, 390);
		}
		else if (game.gameState == Game.STATE.Help)
		{
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			Font fnt3 = new Font("arial", 1, 20);

			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Help", 240, 70);

			g.setFont(fnt3);
			g.drawString("Use WASD keys to move player and dodge enemies", 60, 200);

			g.setFont(fnt2);
			g.drawRect(210, 350, 200, 64);
			g.drawString("Back", 280, 390);
		}
		else if (game.gameState == Game.STATE.End)
		{
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			Font fnt3 = new Font("arial", 1, 20);

			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Game over", 180, 70);

			g.setFont(fnt3);
			g.drawString("You lost with a score of: " + hud.getScore(), 175, 200);

			g.setFont(fnt2);
			g.drawRect(210, 350, 200, 64);
			g.drawString("Try Again", 245, 390);
		}
		else if (game.gameState == Game.STATE.Select)
		{
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);

			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Select Difficulty", 70, 70);

			g.setFont(fnt2);
			g.drawRect(210, 150, 200, 64);
			g.drawString("Normal", 280, 190);

			g.drawRect(210, 250, 200, 64);
			g.drawString("Hard", 280, 290);

			g.drawRect(210, 350, 200, 64);
			g.drawString("Back", 280, 390);
		}

	}

}
