/**
 * Created by tsangwailam on 19/7/14.
 */
package digicrafts.extensions.data {
public class Score {

    public var leaderboard:String;
    public var player:Player;
    public var rank:int;
    public var scoreString:String;
    public var ScoreValue:Number;


    public function Score(obj:*) {

        // Fill in the properties
        for(var key:String in obj){
            if(this.hasOwnProperty(key)){
                if(key=='player') {
                    player=new Player(obj[key]);
                } else {
                    this[key]=obj[key];
                }
            }

        }

    }
}
}
