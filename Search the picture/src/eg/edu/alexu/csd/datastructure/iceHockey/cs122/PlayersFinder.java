package eg.edu.alexu.csd.datastructure.iceHockey.cs122;

import java.awt.Point;
import java.util.Arrays;

public class PlayersFinder implements IPlayersFinder {
	static int topLeftX , topLeftY , botRightX , botRightY ;
	public static int[] getPatternCoordinates(String[] photo ,boolean[][] visited,int team, int row , int col , int count) {
		int[] coordinatesAndArea = {2*topLeftX , 2*topLeftY , 2*botRightX+2 , 2*botRightY+2 , count};
		int[] rowAround = {0,1,-1,0};
		int[] colAround = {-1,0,0,1};
		visited[row][col]=true;
		for(int k=0; k<4;k++) {
			if(row + rowAround[k]>=0 && row + rowAround[k]<photo.length && col + colAround[k]>=0 && col + colAround[k]<photo[0].length() && !visited[row + rowAround[k]][col + colAround[k]] && Character.compare(photo[row + rowAround[k]].charAt(col + colAround[k]), (char)(team+'0'))==0 ){
				count+=1;
				if((col + colAround[k]) < topLeftX ) {
					topLeftX = col + colAround[k];}
				if((col + colAround[k])> botRightX) {
					botRightX = col + colAround[k];}
				if((row + rowAround[k]) > botRightY) {
					botRightY = row + rowAround[k];}
				if((row + rowAround[k]) < topLeftY) {
					topLeftY = row + rowAround[k];}
				coordinatesAndArea = getPatternCoordinates(photo ,visited,team,row + rowAround[k],col + colAround[k] , count);
			}
		}
		return coordinatesAndArea;	
	}
	public static void sort(Point[] coordinates) {
		boolean unsorted = true;
		int temp;
		while(unsorted) {
			unsorted = false;
			for(int i=0; i < coordinates.length-1 ; i++) {
				if(coordinates[i].x > coordinates[i+1].x || (coordinates[i].x == coordinates[i+1].x && coordinates[i].y > coordinates[i+1].y)) {
				unsorted = true;
				temp = coordinates[i].x;
				coordinates[i].x = coordinates[i+1].x;
				coordinates[i+1].x = temp;
				temp = coordinates[i].y;
				coordinates[i].y = coordinates[i+1].y;
				coordinates[i+1].y = temp;
				}
			}
		}
	}
	@Override
	public Point[] findPlayers(String[] photo, int team, int threshold) {
		if(photo == null || photo.length<=0) 
			return null;
		int midX;
		int midY;
		int maxSize = ((photo.length*photo[0].length()*4)/threshold)+1;
		Point[] centreCoordinates = new Point[maxSize];
		int PointCounter=0;
		int count ;
		boolean[][] visited = new boolean[photo.length][photo[0].length()];
		for(int i=0 ; i < photo.length ; i++) {
			for(int j=0 ; j < photo[0].length() ; j++) {
				if(Character.compare(photo[i].charAt(j), (char)(team+'0'))==0 && !visited[i][j]) {
					count = 1;
					topLeftX = botRightX = j;
					topLeftY = botRightY = i;
					int[] patternInfo = getPatternCoordinates(photo ,visited,team,i,j , count);
					if(patternInfo[4]*4>=threshold) {
					midX = (patternInfo[0] + patternInfo[2])/2;
					midY = (patternInfo[1] + patternInfo[3])/2;
					centreCoordinates[PointCounter] = new Point(midX , midY);
					PointCounter+=1;
					}}}}
		Point[] PlayersLocation = new Point[PointCounter];
		for(int k=0 ; k<PointCounter ; k++) {
			PlayersLocation[k] = new Point(centreCoordinates[k].x , centreCoordinates[k].y);
		}
		sort(PlayersLocation);
		return PlayersLocation;
	}
public static void main(String[] args) {
	PlayersFinder op = new PlayersFinder();
	String[] cam = {"8D88888J8L8E888",
			"88NKMG8N8E8JI88",
			"888NS8EU88HN8EO",
			"LUQ888A8TH8OIH8",
			"888QJ88R8SG88TY",
			"88ZQV88B88OUZ8O",
			"FQ88WF8Q8GG88B8",
			"8S888HGSB8FT8S8",
			"8MX88D88888T8K8",
			"8S8A88MGVDG8XK8",
			"M88S8B8I8M88J8N",
			"8W88X88ZT8KA8I8",
			"88SQGB8I8J88W88",
			"U88H8NI8CZB88B8",
			"8PK8H8T8888TQR8"};
	System.out.println(Arrays.toString(op.findPlayers(cam, 8, 9)));
	
}
}

