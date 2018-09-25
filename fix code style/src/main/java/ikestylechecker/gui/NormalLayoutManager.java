/*
 * Copyright 2018 Jonathan Cooper
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to
 * deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or
 * sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package ikestylechecker.gui;

import java.awt.*;
import java.util.*;

/**
 * Constraint for the NormalLayoutManager.
 * If anchors do not force a size on the component, then the preferred size
 * in that dimension is used.
 */
class AnchorSettings
{
	/**
	 * Constant saying no anchor is active for the element.
	 */
	public static final int NO_ANCHOR = -1;

	/**
	 * An anchor used to make the component fill the parent.
	 */
	public static final AnchorSettings ZERO_SETTINGS = new AnchorSettings(0, 0, 0, 0);

	/**
	 * This constraint does not manage a component. The component's behavior
	 * is undefined.
	 */
	public static final AnchorSettings NO_ANCHOR_SETTINGS = new AnchorSettings(NO_ANCHOR, NO_ANCHOR, NO_ANCHOR, NO_ANCHOR);

	/**
	 * The anchor distance from the top.
	 */
	private int top;

	/**
	 * The anchor distance from the right.
	 */
	private int right;

	/**
	 * The anchor distance from the bottom.
	 */
	private int bottom;

	/**
	 * The anchor distance from the left.
	 */
	private int left;

	/**
	 * Initializes a new anchor constraint.
	 *
	 * @param top The anchor distance from the top.
	 *
	 * @param right The anchor distance from the right.
	 *
	 * @param bottom The anchor distance from the bottom.
	 *
	 * @param left The anchor distance from the left.
	 */
	public AnchorSettings(int top, int right, int bottom, int left)
	{
		this.top = top;
		this.right = right;
		this.bottom = bottom;
		this.left = left;
	}

	/**
	 * Gets the anchor distance from the top. Returns zero even if the top is
	 * not managed. Use {@link #anchorsTop()} to check if the top is anchored.
	 */
	public int getTop()
	{
		return Math.max(top, 0);
	}

	/**
	 * Determines if the top should be anchored or not.
	 */
	public boolean anchorsTop()
	{
		return top != -1;
	}

	/**
	 * Gets the anchor distance from the right. Returns zero even if the right
	 * is not managed. Use {@link #anchorsRight()} to check if the right is
	 * anchored.
	 */
	public int getRight()
	{
		return Math.max(right, 0);
	}

	/**
	 * Determines if the right should be anchored or not.
	 */
	public boolean anchorsRight()
	{
		return right != -1;
	}

	/**
	 * Gets the anchor distance from the bottom. Returns zero even if the
	 * bottom is not managed. Use {@link #anchorsBottom()} to check if the
	 * bottom is anchored.
	 */
	public int getBottom()
	{
		return Math.max(bottom, 0);
	}

	/**
	 * Determines if the bottom should be anchored or not.
	 */
	public boolean anchorsBottom()
	{
		return bottom != -1;
	}

	/**
	 * Gets the anchor distance from the left. Returns zero even if the left
	 * is not managed. Use {@link #anchorsLeft()} to check if the left is
	 * anchored.
	 */
	public int getLeft()
	{
		return Math.max(left, 0);
	}

	/**
	 * Determines if the left should be anchored or not.
	 */
	public boolean anchorsLeft()
	{
		return left != -1;
	}
}

/**
 * A layout manager that anchors components to its parent's edges.
 * AnchorSettings is used to anchor it to an edge. If not anchored to both
 * horizontal or both vertical edges, the backup of the preferred size is used.
 * If not edge is anchored, the positioning is undefined.
 */
public class NormalLayoutManager implements LayoutManager2
{
	/**
	 * Saved component anchor constraints.
	 */
	private HashMap<Component, AnchorSettings> componentAnchorSettings;

	/**
	 * Initializes a new NormalLayoutManager. Uses anchors to edges to determine
	 * position and sometimes size.
	 */
	public NormalLayoutManager()
	{
		componentAnchorSettings = new HashMap<Component, AnchorSettings>();
	}

	/**
	 * Adds a component to the layout.
	 *
	 * @param component The component to add constraints to.
	 *
	 * @param constraints The constraints to use for the component.
	 */
	public void addLayoutComponent(Component component, Object constraints)
	{
		if (!(constraints instanceof AnchorSettings))
		{
			throw new IllegalArgumentException("Constraints must be instance of AnchorSettings");
		}
		addAnchorSetting(component, (AnchorSettings)constraints);
	}

	/**
	 * Gets the anchor settings for the component.
	 * Results in the default of AnchorSettings.NO_ANCHOR_SETTINGS if the
	 * component is not managed.
	 */
	public AnchorSettings getAnchorSetting(Component component)
	{
		if (hasAnchorSetting(component))
		{
			return componentAnchorSettings.get(component);
		}
		else
		{
			return AnchorSettings.NO_ANCHOR_SETTINGS;
		}
	}

	/**
	 * Sets the AnchorSettings for the component.
	 *
	 * @param comp The component to add anchor settings to.
	 *
	 * @param settings The constraint to add to the anchor settings.
	 *
	 * @return Returns this layout manager after adding the setting.
	 */
	public NormalLayoutManager addAnchorSetting(Component comp, AnchorSettings settings)
	{
		componentAnchorSettings.put(comp, settings);
		return this;
	}

	/**
	 * Sets the AnchorSettings for the component.
	 *
	 * @param comp The component to add anchor settings to.
	 *
	 * @param top The anchor distance from the top.
	 *
	 * @param right The anchor distance from the right.
	 *
	 * @param bottom The anchor distance from the bottom.
	 *
	 * @param left The anchor distance from the left.
	 */
	public NormalLayoutManager addAnchorSetting(Component comp, int top, int right, int bottom, int left)
	{
		AnchorSettings settings = new AnchorSettings(top, right, bottom, left);
		return addAnchorSetting(comp, settings);
	}

	/**
	 * Checks if there was a constraint applied to a component.
	 *
	 * @return True if settings were applied to the component
	 */
	public boolean hasAnchorSetting(Component comp)
	{
		return componentAnchorSettings.containsKey(comp);
	}

	@Override
	public void addLayoutComponent(String string, Component component)
	{ }

	@Override
	public void removeLayoutComponent(Component component)
	{ }

	@Override
	public void invalidateLayout(Container container)
	{ }

	@Override
	/**
	 * Sets the maximum layout size to the biggest possible.
	 *
	 * @return Dimensions for the largest layout.
	 */
	public Dimension maximumLayoutSize(Container container)
	{
		return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
	}

	@Override
	public float getLayoutAlignmentX(Container target)
	{
		return 0.5f;
	}

	@Override
	public float getLayoutAlignmentY(Container target)
	{
		return 0.5f;
	}

	@Override
	/**
	 * Gets the minimum size of this layout.
	 *
	 * @param container Container to layout.
	 */
	public Dimension minimumLayoutSize(Container container)
	{
		int minWidth = 0;
		int minHeight = 0;
		int numComponents = container.getComponentCount();
		for (int i = 0; i < numComponents; i++)
		{
			Component component = container.getComponent(i);
			Dimension minDims = getCompMinimumSpace(component);
			minWidth = Math.max(minWidth, minDims.width);
			minHeight = Math.max(minHeight, minDims.height);
		}
		return new Dimension(minWidth, minHeight);
	}

	@Override
	/**
	 * Gets the preferred size of this layout. Just uses the minimum size of
	 * this layout.
	 */
	public Dimension preferredLayoutSize(Container container)
	{
		return minimumLayoutSize(container);
	}

	/**
	 * Updates the size and position of all components as necessary.
	 *
	 * @param container Container to layout.
	 */
	public void layoutContainer(Container container)
	{
		int componentCount = container.getComponentCount();
		for (int i = 0; i < componentCount; i++)
		{
			Component component = container.getComponent(i);
			updateVerticalInformation(container, component);
			updateHorizontalInformation(container, component);
			component.doLayout();
		}
	}

	/**
	 * Updates the size and position on the x axis.
	 *
	 * @param container The container to layout.
	 *
	 * @param component The component to update that is a child of the container.
	 */
	private void updateHorizontalInformation(Container container, Component component)
	{
		Insets parentInsets = container.getInsets();
		AnchorSettings setting = getAnchorSetting(component);
		int width = 0;
		int x = 0;
		if (setting.anchorsLeft() && setting.anchorsRight())
		{
			x = setting.getLeft();
			width = container.getWidth() - parentInsets.left - parentInsets.right - setting.getLeft() - setting.getRight();
		}
		else if (setting.anchorsLeft())
		{
			x = setting.getLeft() + parentInsets.left;
			width = container.getPreferredSize().width;
		}
		else if (setting.anchorsRight())
		{
			x = container.getWidth() - parentInsets.right - setting.getRight() - component.getPreferredSize().width;
			width = component.getPreferredSize().width;
		}
		component.setLocation(x, (int)component.getLocation().getY());
		component.setSize(width, component.getSize().height);
	}

	/**
	 * Updates the size and position on the y axis.
	 *
	 * @param container The container to layout.
	 *
	 * @param component The component to update this is a child of the container.
	 */
	private void updateVerticalInformation(Container container, Component component)
	{
		Insets parentInsets = container.getInsets();
		AnchorSettings setting = getAnchorSetting(component);
		int height = 0;
		int y = 0;
		if (setting.anchorsTop() && setting.anchorsBottom())
		{
			y = setting.getTop();
			height = container.getHeight() - parentInsets.top - parentInsets.bottom - setting.getTop() - setting.getBottom();
		}
		else if (setting.anchorsTop())
		{
			y = parentInsets.top + setting.getTop();
			height = component.getPreferredSize().height;
		}
		else if (setting.anchorsBottom())
		{
			y = container.getHeight() - setting.getBottom() - component.getPreferredSize().height - parentInsets.bottom;
			height = component.getPreferredSize().height;
		}
		component.setLocation((int)component.getLocation().getX(), y);
		component.setSize(component.getSize().width, height);
	}

	/**
	 * Gets the minimum space needed for a component.
	 */
	private Dimension getCompMinimumSpace(Component component)
	{
		AnchorSettings setting = getAnchorSetting(component);
		int width = setting.getLeft() + component.getMinimumSize().width + setting.getRight();
		int height = setting.getTop() + component.getMinimumSize().height + setting.getBottom();
		return new Dimension(width, height);
	}
}
