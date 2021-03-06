package com.aeonphyxius.engine;

import com.aeonphyxius.R;

import android.app.Activity;
import android.content.Context;
import android.view.Display;
import android.view.MotionEvent;


public class Engine {
/*Constants that will be used in the game*/
public static enum GAMESTATUS {START, PLAYING, DESTROYED, GAMEOVER,END,LEVEL_COMPLETE};
public static GAMESTATUS GameSatus;
public static final int MAX_LEVEL = 10;

public static final int GAME_THREAD_DELAY = 4000;
public static final int MENU_BUTTON_ALPHA = 0;
public static final boolean HAPTIC_BUTTON_FEEDBACK = true;

public static final float R_VOLUME = 0.3f;
public static final float L_VOLUME = 0.3f;
public static final boolean LOOP_BACKGROUND_MUSIC = true;
public static final int GAME_THREAD_FPS_SLEEP = (1000/60);
public static final int GAME_OVER_THREAD_WAIT = (1000/60)*50;
public static float SCROLL_BACKGROUND_1 = .001f;
public static float SCROLL_BACKGROUND_2 = .003f;
public static float SHOOT_SLEEP = 250;
public static float ANIMATION_SLEEP = 100f;
public static float GAME_OVER_SLEEP = (1000/60) * 5000;
public static float EXPLOSIOM_SLEEP = 50f;

public static final int PLAYER_BANK_LEFT_1 = 1;
public static final int PLAYER_RELEASE = 3;
public static final int PLAYER_BANK_RIGHT_1 = 4;
public static final int PLAYER_FRAMES_BETWEEN_ANI = 9;
public static final float PLAYER_BANK_SPEED = .1f;

public static final float EFFECTS_VOLUME = 0.25f;
public static final String SOUND_CLICK = "click";
public static final String SOUND_CLICK_BACK = "clickBack";
public static final String SOUND_BLASTER ="blaster";
public static final String SOUND_EXPLOSION ="explosion";
public static final String SOUND_EXPLOSION_ENEMY ="explosionEnemy";
public static final String SOUND_LASER_HIT ="laserHit";
public static final String SOUND_FUSHIONSHOT ="fushionShot";

// Vibration
public  static final long PLAYER_DAMAGE_VIB = 100;
public static final long PLAYER_DESTROYED_VIB = 2000;
public  static final long MENU_CLICK_VIB = 10;

// sprites
public static String TEXTURES_FILE = "textures.png";
public static final int SPLASH_SCREEN_MUSIC = R.raw.warfieldedit;


public static final float PLAYER_POS_Y = 0.7f;
public static final float PLAYER_FIRE_START_Y = 1.5f;
public static final float PLAYER_FIRE_START_X = 0.34f;
public static final float MIN_Y = 0.0f;
public static final float MAX_Y = 5.5f;
public static final float FINAL_MAX_X= 5.7f;


public static float INTERCEPTOR_SPEED = 0.014f;
public static float SCOUT_SPEED = 0.012f;
public static float WARSHIP_SPEED = 0.008f;
public static float FINAL1_SPEED = 0.018f;
public static float FINAL2_SPEED = 0.0018f;
public static float FINAL3_SPEED = 0.0018f;
public static float MOVING_OUT_SCOPE = 0.028f;

public static final int TYPE_INTERCEPTOR = 1;
public static final int TYPE_SCOUT = 2;
public static final int TYPE_WARSHIP = 3;
public static final int TYPE_FINAL1 = 10;
public static final int TYPE_FINAL2 = 11;
public static final int TYPE_FINAL3 = 12;

public static final int ATTACK_RANDOM = 0;
public static final int ATTACK_RIGHT = 1;
public static final int ATTACK_LEFT = 2;
public static final float BEZIER_X_1 = 0f;
public static final float BEZIER_X_2 = 3f;
public static final float BEZIER_X_3 = 4.5f;
public static final float BEZIER_X_4 = 6f;
public static final float BEZIER_Y_1 = 0f;
public static final float BEZIER_Y_2 = 4.4f;
public static final float BEZIER_Y_3 = 3.5f;
public static final float BEZIER_Y_4 = 5.6f;
public static final float SQUADRON_MIN_Y = 0.F;
public static final float SQUADRON_START_Y = 5.6f;

//public static final int PLAYER_SHIELDS = 1;
public static final int SCOUT_SHIELDS = 1;
public static final int INTERCEPTOR_SHIELDS = 1;
public static final int WARSHIP_SHIELDS = 2;
public static final int FINAL_SHIELDS = 50;
public static final float PLAYER_BULLET_SPEED = .075f;
public static final float ENEMY_BULLET_SPEED = .040f;

// Player configuration
public static final int DIFF_EASY = 1;
public static final int DIFF_NORMAL = 2;
public static final int DIFF_HARD = 3;
public static final int LIVES_EASY = 5;
public static final int LIVES_NORMAL =4;
public static final int LIVES_HARD = 3;
public static final int SHIELD_EASY = 100;
public static final int SHIELD_NORMAL = 75;
public static final int SHIELD_HARD = 50;
public static final int DAMAGE_EASY = 100;
public static final int DAMAGE_NORMAL = 100;
public static final int DAMAGE_HARD = 75;
public static final int ENGINE_SHIELD_EASY = 10;
public static final int ENGINE_SHIELD_NORMAL = 25;
public static final int ENGINE_SHIELD_HARD = 25;
public static final int ENGINE_DAMAGE_EASY = 10;
public static final int ENGINE_DAMAGE_NORMAL = 25;
public static final int ENGINE_DAMAGE_HARD = 25;

public static final int POINTS_EASY = 5;
public static final int POINTS_NORMAL = 10;
public static final int POINTS_HARD = 10;

public static final int TEXTURE_BG1 = 2;
public static final int TEXTURE_BG2 = 3;
public static final int TEXTURE_PLAYER = 4;
public static final int TEXTURE_PLAYER_RIGHT = 5;
public static final int TEXTURE_PLAYER_LEFT = 6;
public static final int TEXTURE_GAME_OVER = 11;
public static final int TEXTURE_ARROWS = 10;
public static final int TEXTURE_END_LEVEL = 12;

public static final int TEXTURE_FILE_OLD = 0;
public static final int LEFT_TEXTURE_POSITION=1;
public static final int RIGHT_TEXTURE_POSITION=3;

/*Game Variables*/
public static boolean isMuted = false;
public static boolean isVibrated = true;
public static float yScroll = 0.0f;
public static Context context;
public static Display display;
public static int playerFlightAction = 0;
public static MotionEvent event;
public static float playerBankPosX = 1.75f;
public static int difficulty = 2;
public static Activity gameActivity;


}