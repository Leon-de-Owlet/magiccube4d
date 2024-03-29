package com.superliminal.magiccube4d;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.InputEvent;
import java.io.StringReader;
import java.io.PushbackReader;

import javax.swing.JProgressBar;

import com.superliminal.util.ResourceUtils;


/**
 * Represents a simple Applet demo version of the core MagicCube4D puzzle.
 * 
 * Copyright 2005 - Superliminal Software
 * @author Melinda Green
 */
@SuppressWarnings("serial")
public class MC4DApplet extends Applet {
    public MC4DApplet() {
    }
    @Override
	public void init() {
        String lengthstr = getParameter("length");
        int length = lengthstr == null ? 3 : Integer.parseInt(lengthstr);
        System.out.println("length = " + length);
        String logfile = getParameter("logfile"); 
        System.out.println("logfile = " + logfile);
        History hist = new History(length);
        java.net.URL histurl = ResourceUtils.getResource(logfile);
        if(histurl == null)
            System.out.println("couldn't read history file");
        else
            hist.read(new PushbackReader(new StringReader(ResourceUtils.readFileFromURL(histurl))));
        final MC4DView view = new MC4DView(new PuzzleManager("{4,3,3}", 3, new JProgressBar()), new RotationHandler(), hist);
        view.addStickerListener(new MC4DView.StickerListener() {
            public void stickerClicked(InputEvent e, MagicCube.TwistData twisted) {
                view.animate(twisted, true);
            }
        });
        setLayout(new BorderLayout());
        add("Center", view);
    }
}
