package com.aeonphyxius.engine;

import com.aeonphyxius.R;
import android.content.Context;
import android.view.Display;


public class Engine {
/*Constants that will be used in the game*/
public static final int GAME_THREAD_DELAY = 4000;
public static final int MENU_BUTTON_ALPHA = 0;
public static final boolean HAPTIC_BUTTON_FEEDBACK = true;

public static final int R_VOLUME = 100;
public static final int L_VOLUME = 100;
public static final boolean LOOP_BACKGROUND_MUSIC = true;
public static final int GAME_THREAD_FPS_SLEEP = (1000/60);
public static float SCROLL_BACKGROUND_1 = .002f;
public static float SCROLL_BACKGROUND_2 = .007f;
public static float SHOOT_SLEEP = 250f;

public static final int PLAYER_BANK_LEFT_1 = 1;
public static final int PLAYER_RELEASE = 3;
public static final int PLAYER_BANK_RIGHT_1 = 4;
public static final int PLAYER_FRAMES_BETWEEN_ANI = 9;
public static final float PLAYER_BANK_SPEED = .1f;

public static final float EFFECTS_VOLUME = 0.3f;

// sprites
public static int CHARACTER_SHEET = R.drawable.character_sprite;
public static final int WEAPONS_SHEET = R.drawable.destruction;
public static final int BACKGROUND_LAYER_ONE = R.drawable.level_01_bg;
public static final int BACKGROUND_LAYER_TWO = R.drawable.debris;
public static final int PLAYER_SHEET = R.drawable.spaceship_sprite;
public static final int SPLASH_SCREEN_MUSIC = R.raw.warfieldedit;

public static final int TEXTURE_ITEMS = 1;
public static final int TEXTURE_PLAYER = 2;
public static final int LEFT_TEXTURE_POSITION=1;
public static final int RIGHT_TEXTURE_POSITION=3;

public static final float PLAYER_POS_Y = 0.6f;
public static final float PLAYER_FIRE_START_Y = 1.5f;
public static final float PLAYER_FIRE_START_X = 0.34f;


public static int TOTAL_INTERCEPTORS = 10;
public static int TOTAL_SCOUTS = 15;
public static int TOTAL_WARSHIPS = 5;
public static float INTERCEPTOR_SPEED = SCROLL_BACKGROUND_1 * 4f;
public static float SCOUT_SPEED = SCROLL_BACKGROUND_1 * 6f;
public static float WARSHIP_SPEED = SCROLL_BACKGROUND_2 * 4f;
public static final int TYPE_INTERCEPTOR = 1;
public static final int TYPE_SCOUT = 2;
public static final int TYPE_WARSHIP = 3;
public static final int ATTACK_RANDOM = 0;
public static final int ATTACK_RIGHT = 1;
public static final int ATTACK_LEFT = 2;
public static final float BEZIER_X_1 = 0f;
public static final float BEZIER_X_2 = 1f;
public static final float BEZIER_X_3 = 2.5f;
public static final float BEZIER_X_4 = 3f;
public static final float BEZIER_Y_1 = 0f;
public static final float BEZIER_Y_2 = 2.4f;
public static final float BEZIER_Y_3 = 1.5f;
public static final float BEZIER_Y_4 = 2.6f;
public static final float SQUADRON_MIN_Y = 0.F;

public static final int PLAYER_SHIELDS = 1;
public static final int SCOUT_SHIELDS = 1;
public static final int INTERCEPTOR_SHIELDS = 1;

public static final int WARSHIP_SHIELDS = 5;
public static final float PLAYER_BULLET_SPEED = .075f;

// Player configuration
public static final int DIFF_EASY = 1;
public static final int DIFF_NORMAL = 2;
public static final int DIFF_HARD = 3;
public static final int LIVES_EASY = 3;
public static final int LIVES_NORMAL = 2;
public static final int LIVES_HARD = 2;
public static final int SHIELD_EASY = 100;
public static final int SHIELD_NORMAL = 75;
public static final int SHIELD_HARD = 50;
public static final int DAMAGE_EASY = 100;
public static final int DAMAGE_NORMAL = 100;
public static final int DAMAGE_HARD = 75;


/*Game Variables*/

public static boolean isMuted = true;
public static Context context;
public static Thread musicThread;
public static Display display;
public static int playerFlightAction = 0;
public static float playerBankPosX = 1.75f;
public static int difficulty = 2;


}