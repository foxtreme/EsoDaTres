/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoia.noinformada;

import java.awt.Point;
import java.util.List;
import java.util.Vector;
import proyectoia.data.Entorno;
import proyectoia.data.Node;
import proyectoia.data.State;

/**
 *
 * @author chris
 */
public class PreferentePorAmplitud {
    private Node root,solution;
    private Entorno environment;
    private Vector<Node> frontier,explored;
    
    /**
     * Constructor of this class
     */
    public PreferentePorAmplitud(){
        environment = new Entorno();
        environment.loadFile("Prueba4");
        //initial State
        State initialState = environment.getInitialState();
        //root node - state, parent , operator , cost
        root = new Node(initialState,null,0,0);
        //this will contain the solution
        frontier=new Vector<Node>();
        frontier.add(root);
        //this will contain the vectors expanded
        explored= new Vector<Node>();
        //solution is null initially
        solution = null;
    }
    
    /**
     * Creates and returns a vector with the possible operators to apply
     * @param neighbors array corresponding to the valid neighbor cells of the robot
     * @return a vector with the corresponding operators
     */
    private Vector<Integer> generateOperators(int[] neighbors) {
        Vector <Integer> operators = new Vector<Integer>();
        if(neighbors[0]!=1){operators.add(4);}//left
        if(neighbors[1]!=1){operators.add(8);}//up
        if(neighbors[2]!=1){operators.add(6);}//right
        if(neighbors[3]!=1){operators.add(5);}//down
        for(int i=0;i<operators.size();i++){
            System.out.println("operator: "+operators.get(i));
        }
        return operators;
    }
    
    /**
     * Creates a child with a given parent and an operator
     *
     * @param parent Node parent of this child
     * @param operator operator used to generate this child
     * @return Node child of given father Node
     */
    public Node childNode(Node parent, int operator) {
        System.out.println("metas del padre: "+parent.getState().getGoalsAchieved());
        State state = parent.applyOperator(operator, environment, parent.getState());
        Node child = new Node(state, parent, operator, 1);
        child.getState().setGoalsAchieved(parent.getState().getGoalsAchieved());
        System.out.println("metas del hijo: "+child.getState().getGoalsAchieved());
        return child;
    }

    public boolean expand(Node node) {
        //gets the value of the cell where the robot is
        int value = node.getPositionValue(environment);
        System.out.println("robot en: "+node.getState().getPosition().toString());
        System.out.println("value: "+value);
        //if it is an item, increase the goals achieved
        if (value == 6) {
            node.getState().setGoalsAchieved(1);
            node.setPositionValue(environment, 0);
            environment.getOriginalEnv();
        }
        //check if this is a goal
        boolean goal = node.isItGoal(environment);
        if (goal) {
            solution = node;
        }
        return goal;
    }

    public void breadthFirst() {
        boolean loop = true;
        while (loop) {
            //if there are no more nodes to expand, end it
            if (frontier.isEmpty()) {
                loop = false;
            } else {
                //get the first node in the frotier
                Node node = frontier.remove(0);
                
                //add the node to the expanded list
                explored.add(node);
                //expand the node and tell if it's the goal
                loop = !expand(node);
                if (loop) {//if node wasn't the goal
                    Vector <Integer> operators = generateOperators(getEnvironment().getNeighbors(node.getState()));
                    for(int i=0; i<operators.size();i++){
                        Node child = childNode(node,operators.get(i));
                        Point q = child.getState().getPosition();
                        System.out.println(q.x+", "+q.y);
                        frontier.add(child); 
                        
                    }
                    System.out.println("cuantos nodos tiene frontier: "+frontier.size());
                }else{
                    List<Node> path = solution.getPathFromRoot();
                    for(int i=0;i<path.size();i++){
                        Point pos = this.getEnvironment().findRobot(path.get(i).getState());
                        System.out.println(pos.getX()+", "+pos.getY());
                    }
                    System.out.println("Number of Expanded nodes: "+explored.size());
                    System.out.println("Depth of the tree: "+solution.getDepth());
                    loop=false;
                }
            }
        }
    }
    
    /**
     * 
     * @return 
     */
    public Node getRoot(){
        return root;
    }

    public Entorno getEnvironment() {
        return environment;
    }

    public Vector<Node> getFrontier() {
        return frontier;
    }

    public Vector<Node> getExplored() {
        return explored;
    }

    public Node getSolution(){
        return solution;
    }
    
    public static void main(String []args){
        PreferentePorAmplitud ppa = new PreferentePorAmplitud();
        ppa.breadthFirst();
        /*
        Node n0 = ppa.getFrontier().firstElement();
        Point p0 = n0.getState().getPosition();
        System.out.println(p0.x+", "+p0.y);
        boolean b0 = ppa.expand(n0);
        System.out.println("meta?: "+b0);
        System.out.println("cuantas metas?: "+n0.getState().getGoalsAchieved());
        Vector <Integer> op0 = ppa.generateOperators(ppa.getEnvironment().getNeighbors(n0.getState()));
        /////////////////////////////////
        Node n1 = ppa.childNode(n0, op0.get(0));
        Point p1 = n1.getState().getPosition();
        System.out.println(p1.x+", "+p1.y);
        boolean b1 = ppa.expand(n1);
        System.out.println("meta?: "+b1);
        System.out.println("cuantas metas?: "+n1.getState().getGoalsAchieved());
        ///////////////////////////////////
        
        Node n2 = ppa.childNode(n1, 4);       
        Point p2 = n2.getState().getPosition();
        System.out.println(p2.x+", "+p2.y);
        boolean b2 = ppa.expand(n2);
        System.out.println("cuantas metas?: "+n2.getState().getGoalsAchieved());
        System.out.println("meta?: "+b2);
        
        ppa.getEnvironment().getOriginalEnv();
        Node n1 = ppa.childNode(ppa.root, 4);
        Point q = n1.getState().getPosition();
        System.out.println(q.x+", "+q.y);
        System.out.println(p.x+", "+p.y);
        Node n2 = ppa.childNode(ppa.root, 8);
        Point r = n2.getState().getPosition();
        System.out.println(r.x+", "+r.y);
        System.out.println(p.x+", "+p.y);
        */
    }
    
}
