package com.kylediaz.metalgearocelot;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.AffineTransform;

public class WindowScaler extends ComponentAdapter {

    private double scale = 1.0;
    private double paddingLeft, paddingTop;

    private Dimension desiredSize;

    public WindowScaler(Component parent, Dimension desiredSize) {
        parent.addComponentListener(this);
        this.desiredSize = desiredSize;
    }

    public void componentResized(ComponentEvent componentEvent) {
        Component parent = componentEvent.getComponent();
        scale = Math.min((float) parent.getWidth() / desiredSize.width, (float) parent.getHeight() / desiredSize.height);
        paddingLeft = (parent.getWidth() - desiredSize.width * scale) / 2 / scale;
        paddingTop = (parent.getHeight() - desiredSize.height * scale) / 2 / scale;
    }

    public AffineTransform getTransformation() {
        AffineTransform output = new AffineTransform();
        output.scale(scale, scale);
        output.translate(paddingLeft, paddingTop);
        return output;
    }

    public Dimension getDesiredSize() {
        return desiredSize;
    }

    public void setDesiredSize(Dimension desiredSize) {
        this.desiredSize = desiredSize;
    }

}