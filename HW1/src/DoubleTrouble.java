import java.util.Scanner;
import java.util.Random;

public class DoubleTrouble {
    int greenMarkers=0;
    int yellowMarkers=0;
    int orangeMarkers=0;
    int greenMarkerIndex=-1;
    int yellowMarkerIndex=-1;
    int orangeMarkerIndex=-1;
    char[] boardCells;
    int state=0;
    boolean gameOver=false;
    char[] markerColors;
    boolean isPlayerWinner=false;


    public DoubleTrouble(){
        this.greenMarkers=3;
        this.yellowMarkers=7;
        this.orangeMarkers=5;
        this.greenMarkerIndex=-1;
        this.yellowMarkerIndex=2;
        this.orangeMarkerIndex=9;
        this.boardCells= new char[]{'G', 'G', 'G', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'O', 'O', 'O', 'O', 'O'};
        this.state=0;
        this.gameOver=false;
        this.markerColors=new char[]{'G','Y','O'};
        this.isPlayerWinner=false;
    }

    public static void main(String[] args) {
        DoubleTrouble welcome = new DoubleTrouble();
        welcome.WelcomeScreen();
        Scanner sc = new Scanner(System.in);
        int numberOfRounds = welcome.getNumberOfRounds();

        int whoGoesFirst=welcome.getPlayerPreference();
        boolean isPlayerFirst=false;
        if(whoGoesFirst==1)
            isPlayerFirst=true;
        else
            isPlayerFirst=false;
        int PlayerWins=0;
        int ComputerWins=0;
        for (int i = 1; i <= numberOfRounds; i++) {
            System.out.println("Round "+i+":");
            if(isPlayerFirst)
                System.out.println("You go first");
            else
                System.out.println("THe computer goes first");
            DoubleTrouble obj = new DoubleTrouble();
            while (!obj.gameOver) {
                switch (obj.state) {
                    case 0:
                        obj.state = whoGoesFirst;
                        break;
                    case 1:
                        obj.getPlayerTurn();
                        obj.state = 2;
                        break;
                    case 2:
                        obj.getComputerTurn();
                        obj.state = 1;
                        break;
                }
            }
            if(isPlayerFirst) {
                whoGoesFirst = 2;
                isPlayerFirst = false;
            }
            else{
                whoGoesFirst=1;
                isPlayerFirst=true;
            }
            if(obj.isPlayerWinner)
                PlayerWins++;
            else
                ComputerWins++;
            if(PlayerWins>numberOfRounds/2) {
                System.out.println("You are the champion. You only took "+i+" round(s) to win");
                break;
            }
            else if(ComputerWins>numberOfRounds/2){
                System.out.println("The computer is the champion");
                break;
            }
        }
    }

    /**
     * get the computer's turn
     */
    private void getComputerTurn() {
        Random rand=new Random(System.currentTimeMillis());
        System.out.println("Computer's Turn");
        char winningColor=onlyColorLeft();
        if(winningColor!='N'){//There are markers of only one color left on the board, the computer can easily win it
            if(winningColor=='G')
                this.UpdateBoard(winningColor,this.greenMarkers);
            else if(winningColor=='Y')
                this.UpdateBoard(winningColor,this.yellowMarkers);
            else
                this.UpdateBoard(winningColor,this.orangeMarkers);
            System.out.println("The Computer has won, but how? Try again");
            this.gameOver=true;
        }
        else if((this.greenMarkers^this.yellowMarkers^this.orangeMarkers)==0){//Player has made the winning move
            boolean validGeneratedMove=false;
            char randomColor = this.markerColors[rand.nextInt(3)];
            char decidedColor=' ';
            int numberOfMarkers=0;
            boolean dontChooseGreen=false;
            boolean dontChooseYellow=false;
            boolean dontChooseOrange=false;
            while(!validGeneratedMove) {
                if (randomColor == 'G' && !dontChooseGreen) {
                    //System.out.println(this.greenMarkers);
                    if (this.greenMarkers > 0) {
                        numberOfMarkers = rand.nextInt(this.greenMarkers) + 1;
                        validGeneratedMove=true;
                        decidedColor='G';
                    }
                    else {
                        dontChooseGreen = true;
                    }
                } else if (randomColor == 'Y' && !dontChooseYellow) {
                    if (this.yellowMarkers > 0) {
                        numberOfMarkers = rand.nextInt(this.yellowMarkers) + 1;
                        validGeneratedMove=true;
                        decidedColor='Y';
                    }
                    else{
                        dontChooseYellow=true;
                    }
                } else if(randomColor=='O'  && !dontChooseOrange) {
                    if (this.orangeMarkers > 0) {
                        numberOfMarkers = rand.nextInt(this.orangeMarkers) + 1;
                        validGeneratedMove=true;
                        decidedColor='O';
                    }
                    else{
                        dontChooseOrange=true;
                    }
                }
                randomColor = this.markerColors[rand.nextInt(3)];
            }
            this.UpdateBoard(decidedColor,numberOfMarkers);
            if(hasWon()){
                System.out.println("The Computer has won, but how? Try again");
                this.gameOver=true;
            }
            System.out.println("The computer took out "+numberOfMarkers+" "+decidedColor+" markers");
            System.out.println("Board after computer's turn");
            this.PrintBoard();
        }
        else{
            if((this.greenMarkers^this.yellowMarkers)<this.orangeMarkers){
                char markerColor='O';
                int numberOfMarkers=this.orangeMarkers-(this.greenMarkers^this.yellowMarkers);
                System.out.println("The computer took out "+numberOfMarkers+" "+markerColor+" markers");
                this.UpdateBoard(markerColor,numberOfMarkers);

            }
            else if((this.greenMarkers^this.orangeMarkers)<this.yellowMarkers){
                char markerColor='Y';
                int numberOfMarkers=this.yellowMarkers-(this.greenMarkers^this.orangeMarkers);
                System.out.println("The computer took out "+numberOfMarkers+" "+markerColor+" markers");
                this.UpdateBoard(markerColor,numberOfMarkers);
            }
            else{
                char markerColor='G';
                int numberOfMarkers=this.greenMarkers-(this.yellowMarkers^this.orangeMarkers);
                System.out.println("The computer took out "+numberOfMarkers+" "+markerColor+" markers");
                this.UpdateBoard(markerColor,numberOfMarkers);
            }
            if(hasWon()){
                System.out.println("The Computer has won, but how? Try again");
                this.gameOver=true;
            }
            else {
                System.out.println("Board after computer's turn");
                this.PrintBoard();
            }
        }
        //this.gameOver=true;
    }

    /**
     * Check if only one color is left
     * @return that only color remaining else return N
     * Used by the computer to play a winning move
     */
    char onlyColorLeft(){
        if(this.greenMarkers==0 && this.yellowMarkers==0){
            return 'O';
        }
        else if(this.greenMarkers==0 && this.orangeMarkers==0){
            return 'Y';
        }
        else if(this.yellowMarkers==0 && this.orangeMarkers==0){
            return 'G';
        }
        else{
            return 'N';
        }
    }

    /**
     * getPlayerTurn
     * Takes User input as their turn. Check if the move is valid or not. Keep on going through a loop until a valid turn(more about valid() where it is declared) is taken in
     * Once a valid turn is taken in, update the board(more about UpdateBoard where it is defined.
     * Check if the player has won or not(more about hasWon() where it is defined
     */
    private void getPlayerTurn() {
        Scanner sc=new Scanner(System.in);
        char markerColor;
        int numberOfMarkers=0;
        System.out.println("Player's turn");
        this.PrintBoard();
        this.getBoardStatus();
        while(true){
            System.out.println("Enter marker color: G, Y, or O");
            markerColor=sc.next().charAt(0);
            numberOfMarkers=this.getNumberOfMarkers();
            if(this.valid(markerColor,numberOfMarkers)) {
                this.UpdateBoard(markerColor,numberOfMarkers);
                break;
            }
        }
        System.out.println("Taking out "+numberOfMarkers+" "+markerColor+" markers");
        if(hasWon()){
            System.out.println("You have won!!!");
            this.isPlayerWinner=true;
            this.gameOver=true;
        }
        else {
            System.out.println("Board after player's turn:");
            this.PrintBoard();
        }
    }

    /**
     * valid
     * @param markerColor-Color of selected marker
     * @param numberOfMarkers-Number of markers of a particular color to be taken out
     * @return true if combination is valid
     */
    private boolean valid(char markerColor,int numberOfMarkers) {
        if(numberOfMarkers<=0){//Player can't take out 0 or negative markers
            System.out.println("You have to remove >0 number of markers");
            return false;
        }
        else {
            if (markerColor == 'G') {
                if (numberOfMarkers <= this.greenMarkers && this.greenMarkers > 0) {//Verify if you have enough markers to take out or not
                    return true;
                } else if (this.greenMarkers <= 0) {
                    System.out.println("All green markers are already taken out.");
                    return false;
                } else {
                    System.out.println("Not enough markers to take out. You can take out " + this.greenMarkers + " green markers.");
                    return false;
                }
            } else if (markerColor == 'Y') {
                if (numberOfMarkers <= this.yellowMarkers && this.yellowMarkers > 0) {//Verify if you have enough markers to take out or not
                    return true;
                } else if (this.yellowMarkers <= 0) {
                    System.out.println("All yellow markers are already taken out.");
                    return false;
                } else {
                    System.out.println("Not enough markers to take out. You can take out " + this.yellowMarkers + " yellow markers.");
                    return false;
                }
            } else if (markerColor == 'O') {
                if (numberOfMarkers <= this.orangeMarkers && this.orangeMarkers > 0) {//Verify if you have enough markers to take out or not
                    return true;
                } else if (this.orangeMarkers <= 0) {
                    System.out.println("All orange markers are already taken out.");
                    return false;
                } else {
                    System.out.println("Not enough markers to take out. You can take out " + this.orangeMarkers + " orange markers.");
                    return false;
                }
            } else {
                System.out.println("Wrong marker color entered. Enter G, Y, or O");
                return false;
            }
        }
    }

    /**
     * UpdateBoard
     * Reduces the number of markers of that color that can be taken out. Sets the taken out marker as 'B' to mark os taken out
     * @param markerColor-color of marker
     * @param numberOfMarkers-number of markers to be taken out
     */
    private void UpdateBoard(char markerColor,int numberOfMarkers) {
        if(markerColor=='G'){
            this.greenMarkers-=numberOfMarkers;
            for(int i=0;i<numberOfMarkers;i++){
                this.greenMarkerIndex++;//Increase index to indicate where to look at in the Board array
                this.boardCells[this.greenMarkerIndex]='B';
            }
        }
        else if(markerColor=='Y'){
            this.yellowMarkers-=numberOfMarkers;
            for(int i=0;i<numberOfMarkers;i++){
                this.yellowMarkerIndex++;//Increase index to indicate where to look at in the Board array
                this.boardCells[this.yellowMarkerIndex]='B';
            }
        }
        else{
            this.orangeMarkers-=numberOfMarkers;
            for(int i=0;i<numberOfMarkers;i++){
                this.orangeMarkerIndex++;//Increase index to indicate where to look at in the Board array
                this.boardCells[this.orangeMarkerIndex]='B';
            }
        }
    }

    /**
     * hasWon()
     * @return true if the player who just made the move won or not
     * If number of markers is 0 after a turn, means that player has taken out the last marker
     */
    boolean hasWon(){
        if(this.greenMarkers+this.yellowMarkers+this.orangeMarkers==0) {
            this.gameOver=true;//setting the gameOver variable to true to break the while loop
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Print the welcome screen
     */
    void WelcomeScreen(){
        System.out.println("Welcome to the");
        System.out.println("      ##### ##                              /      ###                   /###           /                                  /      ###              ");
        System.out.println("   /#####  /##                            #/        ###                 /  ############/                                 #/        ###             ");
        System.out.println(" //    /  / ###                           ##         ##                /     #########                                   ##         ##             ");
        System.out.println("/     /  /   ###                          ##         ##                #     /  #                                        ##         ##             ");
        System.out.println("     /  /     ###                         ##         ##                 ##  /  ##                                        ##         ##             ");
        System.out.println("    ## ##      ##    /###   ##   ####     ## /###    ##      /##           /  ###     ###  /###     /###   ##   ####     ## /###    ##      /##    ");
        System.out.println("    ## ##      ##   / ###  / ##    ###  / ##/ ###  / ##     / ###         ##   ##      ###/ #### / / ###  / ##    ###  / ##/ ###  / ##     / ###   ");
        System.out.println("    ## ##      ##  /   ###/  ##     ###/  ##   ###/  ##    /   ###        ##   ##       ##   ###/ /   ###/  ##     ###/  ##   ###/  ##    /   ###  ");
        System.out.println("    ## ##      ## ##    ##   ##      ##   ##    ##   ##   ##    ###       ##   ##       ##       ##    ##   ##      ##   ##    ##   ##   ##    ### ");
        System.out.println("    ## ##      ## ##    ##   ##      ##   ##    ##   ##   ########        ##   ##       ##       ##    ##   ##      ##   ##    ##   ##   ########  ");
        System.out.println("    #  ##      ## ##    ##   ##      ##   ##    ##   ##   #######          ##  ##       ##       ##    ##   ##      ##   ##    ##   ##   #######   ");
        System.out.println("       /       /  ##    ##   ##      ##   ##    ##   ##   ##                ## #      / ##       ##    ##   ##      ##   ##    ##   ##   ##        ");
        System.out.println("  /###/       /   ##    ##   ##      /#   ##    /#   ##   ####    /          ###     /  ##       ##    ##   ##      /#   ##    /#   ##   ####    / ");
        System.out.println(" /   ########/     ######     ######/ ##   ####/     ### / ######/            ######/   ###       ######     ######/ ##   ####/     ### / ######/  ");
        System.out.println("/       ####        ####       #####   ##   ###       ##/   #####               ###      ###       ####       #####   ##   ###       ##/   #####   ");
        System.out.println("#                                                                                                                                                  ");
        System.out.println(" ##                                                                                                                                                ");
        System.out.println("/###           /                                                                                                      ");
        System.out.println("/  ############/                                                                                                      ");
        System.out.println("/     #########                                                                                                  #    ");
        System.out.println("#     /  #                                                                                                      ##    ");
        System.out.println(" ##  /  ##                                                                                                      ##    ");
        System.out.println("    /  ###          /###   ##   ####    ###  /###   ###  /###     /###   ### /### /###     /##  ###  /###     ########");
        System.out.println("   ##   ##         / ###  / ##    ###  / ###/ #### / ###/ #### / / ###  / ##/ ###/ /##  / / ###  ###/ #### / ######## ");
        System.out.println("   ##   ##        /   ###/  ##     ###/   ##   ###/   ##   ###/ /   ###/   ##  ###/ ###/ /   ###  ##   ###/     ##    ");
        System.out.println("   ##   ##       ##    ##   ##      ##    ##          ##    ## ##    ##    ##   ##   ## ##    ### ##    ##      ##    ");
        System.out.println("   ##   ##       ##    ##   ##      ##    ##          ##    ## ##    ##    ##   ##   ## ########  ##    ##      ##    ");
        System.out.println("    ##  ##       ##    ##   ##      ##    ##          ##    ## ##    ##    ##   ##   ## #######   ##    ##      ##    ");
        System.out.println("     ## #      / ##    ##   ##      ##    ##          ##    ## ##    ##    ##   ##   ## ##        ##    ##      ##    ");
        System.out.println("      ###     /  ##    ##   ##      /#    ##          ##    ## ##    /#    ##   ##   ## ####    / ##    ##      ##    ");
        System.out.println("       ######/    ######     ######/ ##   ###         ###   ### ####/ ##   ###  ###  ### ######/  ###   ###     ##    ");
        System.out.println("         ###       ####       #####   ##   ###         ###   ### ###   ##   ###  ###  ### #####    ###   ###     ##   ");
        System.out.println("About the tournament:");
        System.out.println("There will an odd number of rounds to find the indisputable champion. You get to select the number of rounds.");
        System.out.println("In each round:");
        System.out.println("There are 3 Green markers, 7 Yellow Markers, and 5 Orange Markers.");
        System.out.println("Select the number of markers of a particular color to be taken out.");
        System.out.println("Last person to take out a marker wins.");
        System.out.println("G=Green Marker, Y=Yellow Marker, O=Orange Marker, and B=Black/Removed.");
        System.out.println("Game Board:");
        PrintBoard();
    }

    /**
     * PrintBoard()
     * Print the player board
     */
    public void PrintBoard(){
        System.out.println("         "+this.boardCells[0]+"         ");
        System.out.println("       "+this.boardCells[1]+"   "+this.boardCells[2]+"       ");
        System.out.println("     "+this.boardCells[3]+"   "+this.boardCells[4]+"   "+this.boardCells[5]+"     ");
        System.out.println("   "+this.boardCells[6]+"   "+this.boardCells[7]+"   "+this.boardCells[8]+"   "+this.boardCells[9]+"   ");
        System.out.println(" "+this.boardCells[10]+"   "+this.boardCells[11]+"   "+this.boardCells[12]+"   "+this.boardCells[13]+"   "+this.boardCells[14]+" ");
    }

    /**
     * getPlayerPreference
     * @return 1 if player wants to go first, else 2 to signify that the computer is going first
     */
    public int getPlayerPreference(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Choose if you want to go first or not. You will alternate between going first and second each round.");
        while(true) {
            System.out.println("If you want to go first, enter Y, else N.");
            char c=sc.next().charAt(0);
            if(c=='Y')
                return 1;
            else if(c=='N')
                return 2;
            else
                System.out.println("You entered:"+c+". Please enter Y or N");
        }
    }

    /**
     * getNumberOfRounds
     * @return-the number of rounds(must be odd)
     */
    int getNumberOfRounds(){
        Scanner sc=new Scanner(System.in);
        int numberOfRounds=0;
            System.out.println("Enter an odd number of rounds:");
            try{
                numberOfRounds = sc.nextInt();
                if (numberOfRounds % 2 == 0)
                    throw new Exception("Number must be odd");
            } catch (Exception e) {
                return getNumberOfRounds();
            }
        return numberOfRounds;
    }

    /**
     * getNumberOfRMarkers
     * @return-the number of markers selected by user
     */
    int getNumberOfMarkers(){
        Scanner sc=new Scanner(System.in);
        int numberOfMarkers=0;
        System.out.println("Enter number of markers to take out.");
        try{
            numberOfMarkers = sc.nextInt();
            }catch (Exception e){
                return getNumberOfMarkers();
        }
        return numberOfMarkers;
    }

    /**
     * getBoardStatus
     * Prints the number of different color markers that can be taken out
     */
    void getBoardStatus(){
        System.out.println("You can take out "+this.greenMarkers+" Green markers");
        System.out.println("You can take out "+this.yellowMarkers+" Yellow markers");
        System.out.println("You can take out "+this.orangeMarkers+" Orange markers");
    }
}
