package com.oopfinal.main;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

/**
 * This class maintain, update and render all objects
 */
public class Handler
{

	ArrayList<GameObject> object = new ArrayList<GameObject>();
	
	/**
	 * The update state method
	 * 
	 */
	public void tick()
	{
		for (int i = 0; i < object.size(); i++)
		{
			GameObject tempObject = object.get(i);
			if (tempObject == null)
				continue;
			tempObject.tick();
		}
	}
	/**
	 * The renderer method
	 * @param g The Graphic object to draw
	 */
	public void render(Graphics g)
	{
		for (int i = 0; i < object.size(); i++)
		{
			GameObject tempObject = object.get(i);
			if (tempObject == null)
				continue;
			tempObject.render(g);
		}
	}
	/**
	 *  Clear all enemy except Player
	 */
	public void clearEnemys()
	{
		for (int i = 0; i < object.size(); i++)
		{
			GameObject tempObject = object.get(i);

			if (tempObject.getId() == ID.Player)
			{
				object.clear();
				if (Game.gameState != Game.STATE.End)
					addObject(new Player((int) tempObject.getX(), (int) tempObject.getY(), ID.Player, this));
			}
		}
	}
	/**
	 * The insert object method
	 * @param object The object want to put into the handler
	 */
	public void addObject(GameObject object)
	{
		this.object.add(object);
	}
	/**
	 * The remove object method
	 * @param object The object want to remove into the handler
	 */
	public void removeObject(GameObject object)
	{
		this.object.remove(object);
	}
}