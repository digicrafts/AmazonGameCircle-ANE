/**
 * Created by tsangwailam on 17/7/14.
 */
package digicrafts.extensions.data {
public class Achievement {

    public var id:String;
    public var title:String;
    public var description:String;
    public var imageURL:String;
    public var pointValue:int;
    public var position:int;
    public var progress:Number;
    public var isHidden:Boolean;
    public var isUnlocked:Boolean;

    public function Achievement(obj:*) {

        // Fill in the properties
        for(var key:String in obj){
            if(this.hasOwnProperty(key))
                this[key]=obj[key];
        }

    }
}
}
