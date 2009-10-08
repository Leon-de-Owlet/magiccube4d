import java.awt.Color;


/**
 * Contains all the global static constants shared by all Magic Cube 4D classes.
 * 
 * Copyright 2005 - Superliminal Software
 * @author Melinda Green & Don Hatch
 */
public class MagicCube {
	
	/*
	 * Constants that probably shouldn't be changed
	 */
	public final static int 
		NDIMS          = 4,
        DEFAULT_LENGTH = 3;;
    
	/**
	 * Axes
	 */
	public final static int
		X = 0,
		Y = 1,
		Z = 2,
		W = 3;
    
	/**
	 * Rotation directions
	 */
	public final static int 
		CCW =  1,
		CW  = -1;

    // disallows instantiation of utility class
    private MagicCube() {}

    /**
     * Indexing data for a sticker or a grip.
     */
    public final static class Stickerspec {
        public int coords[] = new int[NDIMS];	// XXX - only applies to old puzzles now, try to remove.
        public int
            face, dim, id_within_puzzle, id_within_face;
    }
    
    
    /**
     * Simple data class to hold an animation specification.
     * A queue of these could be used to set up a sequence of moves.
     */
    public static class TwistData {
        public MagicCube.Stickerspec grip;
        public int animStep=0, slicemask, direction;
        public TwistData(MagicCube.Stickerspec grip, int direction, int slicemask) {
            this.grip = grip; this.slicemask = slicemask; this.direction = direction;
        }
        public TwistData(int id_within_puzzle, int direction, int slicemask) {
            this.direction = direction;
            this.slicemask = slicemask;
            this.grip = new MagicCube.Stickerspec();
            grip.id_within_puzzle = id_within_puzzle;
        }
    }
    
	// Helper functions
    public static float DTOR(float d) { return (float) (d * Math.PI / 180); }
	public static int SGN(float x) { return x<0 ? -1 : x>0 ? 1 :0; }
	public static float RTOD(float r) { return (float) (r / Math.PI * 180); }
	public static boolean BIT(int mask, int bit) { return (mask & (1<<bit)) != 0; }
    public static int HOWMANY(int x, int mod) { return (x + mod - 1) / mod; }
    public static int ROUNDUP(int x, int mod) { return HOWMANY(x,mod)*mod; }  /* only works for >= 0 */
    public static int ROUNDDOWN(int x, int mod) { return x / mod * mod; }  /* ditto */

    
	/*
	 * default 4d viewing parameters
	 */
	public final static float 
		FACESHRINK    = .4f,
		STICKERSHRINK = .5f,
		EYEW          = 5.2f;

	/*
	 * 3d viewing parameters
	 */
	public final static float
		TILT  =  30, // degrees
		TWIRL = -42, // degrees
		EYEZ  =  10; // should really be function of LENGTH and EYEW

    public final static float SUNVEC[] = { .82f, 1.55f, 3.3f }; // points *towards* sun. default for shading & shadows

	public final static String 
        TITLE = "Magic Cube 4D", 
        LOGFILE = "MagicCube4D.log", // in user's home dir
        MAGIC_NUMBER = "MagicCube4D"; // 1st string in log file for sanity checking
    
    public final static String PUZZLE_VERSION = "4.0.0";
    public final static int LOG_FILE_VERSION = 3;
    public final static int MACRO_FILE_VERSION = 2;
    
    public final static Color
        SKY    = new Color(20,170,235),
        GROUND = new Color(20, 130, 20);
}