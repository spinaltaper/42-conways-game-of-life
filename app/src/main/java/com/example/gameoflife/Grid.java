package com.example.gameoflife;

public class Grid {
    boolean[][] gridData = new boolean[100][100];

    int getNeighbors(int x, int y){
        int neighbors = 0;
        if(this.gridData[x-1][y-1]){
            neighbors++;
        }
        if(this.gridData[x][y-1]){
            neighbors++;
        }
        if(this.gridData[x+1][y-1]){
            neighbors++;
        }
        if(this.gridData[x-1][y]){
            neighbors++;
        }
        if(this.gridData[x+1][y]){
            neighbors++;
        }
        if(this.gridData[x-1][y+1]){
            neighbors++;
        }
        if(this.gridData[x][y+1]){
            neighbors++;
        }
        if(this.gridData[x+1][y+1]){
            neighbors++;
        }
        return neighbors;
    }

    public void toggle(int xx, int yy){
        if(this.gridData[xx][yy]==true){
            this.gridData[xx][yy]=false;
            return;
        }
        this.gridData[xx][yy]=true;
    }

    public void tick(){
        for(int i = 0; i<100;i++) {
            for (int j = 0; j < 100; j++) {
                int neighborCount = getNeighbors(i,j);
                boolean currState = gridData[i][j];
                switch(neighborCount){
                    case 2: gridData[i][j]=currState;
                    break;
                    case 3: gridData[i][j]=true;
                    break;
                    default: gridData[i][j]=false;
                    break;
                }
            }
        }
    }
}
