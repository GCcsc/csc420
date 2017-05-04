/*
 * The MIT License
 *
 * Copyright 2017 chasetoy.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
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
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package csc420.gui;

import csc420.AppEventManager;
import csc420.models.TwitterUser;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.CrossoverScalingControl;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.GraphMouseListener;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ScalingControl;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.picking.PickedState;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.util.Collection;
import javax.swing.JPanel;

/**
 *
 * @author chasetoy
 */
public class ResultsPane extends JPanel {
    VisualizationViewer<TwitterUser, String> vv;
    Layout<TwitterUser, String> layout;
    DirectedSparseGraph<TwitterUser, String> dg;
    PickedState<TwitterUser> pickedState ;
    
    TwitterUser currentUser;
    public ResultsPane() {

        dg = new DirectedSparseGraph<>();
        layout = new FRLayout<>(dg);
        vv = new VisualizationViewer<>(layout);
        
        initComponents();

    }
    
    public void getData(TwitterUser currentUser, Collection<TwitterUser> twitterUsers) {

        this.currentUser = currentUser;
        
        dg = new DirectedSparseGraph<>();
        layout.setGraph(dg);
        vv.setGraphLayout(layout);
        
        dg.addVertex(this.currentUser);
        
        for(TwitterUser user : twitterUsers) {
            if(user.equals(this.currentUser)) 
                break;
            dg.addVertex(user);

            dg.addEdge(
                    String.valueOf(user.getId()),
                    user,
                    this.currentUser,
                    EdgeType.DIRECTED
            );

        }

    }
    
    private void initComponents(){
        AppEventManager.setResultsPanel(this);
        
        

        setOpaque(true);
        Color twitter = new Color(0,204,255);
        setBackground(twitter);
        Dimension dim1 = getPreferredSize();
        dim1.width = 500;
        setPreferredSize(dim1);
        
        layout.setSize(new Dimension(500, 500));
        vv.setPreferredSize(new Dimension(500, 500));
        
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<>());
        //vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller<>());
        
        DefaultModalGraphMouse<TwitterUser, String> gm = new DefaultModalGraphMouse<>();
        gm.setMode(ModalGraphMouse.Mode.TRANSFORMING);
        vv.setGraphMouse(gm);
        
        vv.addGraphMouseListener(new GraphMouseListener<TwitterUser>() {
            @Override
            public void graphClicked(TwitterUser v, MouseEvent me) {
                AppEventManager.apiGetUserByName(v.getHandle());
            }

            @Override
            public void graphPressed(TwitterUser v, MouseEvent me) {
            }

            @Override
            public void graphReleased(TwitterUser v, MouseEvent me) {
            }
        });
        
        pickedState = vv.getPickedVertexState();
        
        pickedState.addItemListener((ItemEvent e) -> {
            System.out.println("Wait....what was that!?");
            Object object = e.getItem();
            if(object instanceof TwitterUser) {
                System.out.println("Oh okay! Well let's give it a look!");
                TwitterUser user = (TwitterUser) object;
                if(pickedState.isPicked(user)) {
                    System.out.println(user);
                    AppEventManager.apiGetUserByName(user.getHandle());
                }
            }
        });
        
        ScalingControl scaling = new CrossoverScalingControl();
        scaling.scale(vv, CENTER_ALIGNMENT, vv.getCenter());
        layout.initialize();
        add(vv);
    }

}
