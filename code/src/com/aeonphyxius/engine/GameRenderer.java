package com.aeonphyxius.engine;

import java.util.Random;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import com.aeonphyxius.gamecomponents.drawable.Enemy;
import com.aeonphyxius.gamecomponents.drawable.Player;
import com.aeonphyxius.gamecomponents.manager.BackGroundManager;
import com.aeonphyxius.gamecomponents.manager.EnemyManager;
import com.aeonphyxius.gamecomponents.manager.WeaponManager;
import android.opengl.GLSurfaceView.Renderer;

/**
* GameRenderer Object.
* 
* <P>All related rendering operations.
*  
* <P>Note that {@link BigDecimal} is used to model the price - not double or float. 
* See {@link #Guitar(String, BigDecimal, Integer)} for more information.
*  
* @author Alejandro Santiago
* @version 1.0
* @email alejandro@aeonphyxius.com - asantiago@uoc.edu
*/

public class GameRenderer implements Renderer {

	private Player player1 = new Player();
	// private Enemy[] enemies = new Enemy[Engine.TOTAL_INTERCEPTORS +
	// Engine.TOTAL_SCOUTS + Engine.TOTAL_WARSHIPS - 1];
	private Texture textureLoader;
	private int[] spriteSheets = new int[4];
	// private Weapon[] playerFire = new Weapon[4];

	//private int goodGuyBankFrames = 0;
	private long loopStart = 0;
	private long loopEnd = 0;
	private long loopRunTime = 0;

	@Override
	public void onDrawFrame(GL10 gl) {
		loopStart = System.currentTimeMillis();
		// TODO Auto-generated method stub
		try {
			if (loopRunTime < Engine.GAME_THREAD_FPS_SLEEP) {
				Thread.sleep(Engine.GAME_THREAD_FPS_SLEEP - loopRunTime);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

		BackGroundManager.getInstance().scrollBackground(gl);

		movePlayer1(gl);
		moveEnemy(gl);

		detectCollisions();

		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_ONE, GL10.GL_ONE_MINUS_SRC_ALPHA);
		loopEnd = System.currentTimeMillis();
		loopRunTime = ((loopEnd - loopStart));
	}

	private void initializeInterceptors() {
		for (int x = 0; x < Engine.TOTAL_INTERCEPTORS - 1; x++) {
			Enemy interceptor = new Enemy(Engine.TYPE_INTERCEPTOR,
					Engine.ATTACK_RANDOM);
			EnemyManager.getInstance().getEnemyList().add(x, interceptor);
		}
	}

	private void initializeScouts() {
		for (int x = Engine.TOTAL_INTERCEPTORS - 1; x < Engine.TOTAL_INTERCEPTORS
				+ Engine.TOTAL_SCOUTS - 1; x++) {
			Enemy interceptor;
			if (x >= (Engine.TOTAL_INTERCEPTORS + Engine.TOTAL_SCOUTS) / 2) {
				interceptor = new Enemy(Engine.TYPE_SCOUT, Engine.ATTACK_RIGHT);
			} else {
				interceptor = new Enemy(Engine.TYPE_SCOUT, Engine.ATTACK_LEFT);
			}
			EnemyManager.getInstance().getEnemyList().add(x, interceptor);
		}
	}

	private void initializeWarships() {
		for (int x = Engine.TOTAL_INTERCEPTORS + Engine.TOTAL_SCOUTS - 1; x < Engine.TOTAL_INTERCEPTORS
				+ Engine.TOTAL_SCOUTS + Engine.TOTAL_WARSHIPS - 1; x++) {
			Enemy interceptor = new Enemy(Engine.TYPE_WARSHIP,
					Engine.ATTACK_RANDOM);
			EnemyManager.getInstance().getEnemyList().add(x, interceptor);
		}
	}

	private void moveEnemy(GL10 gl) {
		for (int x = 0; x < Engine.TOTAL_INTERCEPTORS + Engine.TOTAL_SCOUTS	+ Engine.TOTAL_WARSHIPS - 1; x++) {
			if (!EnemyManager.getInstance().getEnemyList().get(x).isDestroyed) {
				Random randomPos = new Random();
				switch (EnemyManager.getInstance().getEnemyList().get(x).enemyType) {
				
				case Engine.TYPE_INTERCEPTOR:
					if (EnemyManager.getInstance().getEnemyList().get(x).posY < 0) {
						EnemyManager.getInstance().getEnemyList().get(x).posY = (randomPos.nextFloat() * 4) + 4;
						EnemyManager.getInstance().getEnemyList().get(x).posX = randomPos.nextFloat() * 3;
						EnemyManager.getInstance().getEnemyList().get(x).isLockedOn = false;
						EnemyManager.getInstance().getEnemyList().get(x).lockOnPosX = 0;
					}
					gl.glMatrixMode(GL10.GL_MODELVIEW);
					gl.glLoadIdentity();
					gl.glPushMatrix();
					gl.glScalef(.15f, .15f, 1f);

					if (EnemyManager.getInstance().getEnemyList().get(x).posY >= 3) {
						EnemyManager.getInstance().getEnemyList().get(x).posY -= Engine.INTERCEPTOR_SPEED;
					} else {
						if (!EnemyManager.getInstance().getEnemyList().get(x).isLockedOn) {
							EnemyManager.getInstance().getEnemyList().get(x).lockOnPosX = Engine.playerBankPosX;
							EnemyManager.getInstance().getEnemyList().get(x).isLockedOn = true;
							EnemyManager.getInstance().getEnemyList().get(x).incrementXToTarget = (float) ((EnemyManager
									.getInstance().getEnemyList().get(x).lockOnPosX - EnemyManager
									.getInstance().getEnemyList().get(x).posX) / (EnemyManager
											.getInstance().getEnemyList().get(x).posY / (Engine.INTERCEPTOR_SPEED * 4)));
						}
						EnemyManager.getInstance().getEnemyList().get(x).posY -= (Engine.INTERCEPTOR_SPEED * 4);
						EnemyManager.getInstance().getEnemyList().get(x).posX += EnemyManager
								.getInstance().getEnemyList().get(x).incrementXToTarget;

					}
					gl.glTranslatef(EnemyManager.getInstance().getEnemyList()
							.get(x).posX, EnemyManager.getInstance()
							.getEnemyList().get(x).posY, 0f);
					gl.glMatrixMode(GL10.GL_TEXTURE);
					gl.glLoadIdentity();
					gl.glTranslatef(0.25f, .25f, 0.0f);
					EnemyManager.getInstance().getEnemyList().get(x).draw(gl, spriteSheets);
					gl.glPopMatrix();
					gl.glLoadIdentity();

					break;
				
				case Engine.TYPE_SCOUT:
					if (EnemyManager.getInstance().getEnemyList().get(x).posY <= 0) {
						EnemyManager.getInstance().getEnemyList().get(x).posY = (randomPos.nextFloat() * 4) + 4;
						EnemyManager.getInstance().getEnemyList().get(x).isLockedOn = false;
						EnemyManager.getInstance().getEnemyList().get(x).posT = Engine.SCOUT_SPEED;
						EnemyManager.getInstance().getEnemyList().get(x).lockOnPosX = EnemyManager
								.getInstance().getEnemyList().get(x)
								.getNextScoutX();
						EnemyManager.getInstance().getEnemyList().get(x).lockOnPosY = EnemyManager
								.getInstance().getEnemyList().get(x)
								.getNextScoutY();
						if (EnemyManager.getInstance().getEnemyList().get(x).attackDirection == Engine.ATTACK_LEFT) {
							EnemyManager.getInstance().getEnemyList().get(x).posX = 0;
						} else {
							EnemyManager.getInstance().getEnemyList().get(x).posX = 3f;
						}
					}
					gl.glMatrixMode(GL10.GL_MODELVIEW);
					gl.glLoadIdentity();
					gl.glPushMatrix();
					gl.glScalef(.15f, .15f, 1f);

					if (EnemyManager.getInstance().getEnemyList().get(x).posY >= 2.75f) {
						EnemyManager.getInstance().getEnemyList().get(x).posY -= Engine.SCOUT_SPEED;

					} else {
						EnemyManager.getInstance().getEnemyList().get(x).posX = EnemyManager
								.getInstance().getEnemyList().get(x)
								.getNextScoutX();
						EnemyManager.getInstance().getEnemyList().get(x).posY = EnemyManager
								.getInstance().getEnemyList().get(x)
								.getNextScoutY();
						EnemyManager.getInstance().getEnemyList().get(x).posT += Engine.SCOUT_SPEED;

					}
					gl.glTranslatef(EnemyManager.getInstance().getEnemyList()
							.get(x).posX, EnemyManager.getInstance()
							.getEnemyList().get(x).posY, 0f);
					gl.glMatrixMode(GL10.GL_TEXTURE);
					gl.glLoadIdentity();
					gl.glTranslatef(0.50f, .25f, 0.0f);
					EnemyManager.getInstance().getEnemyList().get(x)
					.draw(gl, spriteSheets);
					gl.glPopMatrix();
					gl.glLoadIdentity();

					break;
				
				case Engine.TYPE_WARSHIP:
					if (EnemyManager.getInstance().getEnemyList().get(x).posY < 0) {
						EnemyManager.getInstance().getEnemyList().get(x).posY = (randomPos.nextFloat() * 4) + 4;
						EnemyManager.getInstance().getEnemyList().get(x).posX = randomPos.nextFloat() * 3;
						EnemyManager.getInstance().getEnemyList().get(x).isLockedOn = false;
						EnemyManager.getInstance().getEnemyList().get(x).lockOnPosX = 0;
					}
					gl.glMatrixMode(GL10.GL_MODELVIEW);
					gl.glLoadIdentity();
					gl.glPushMatrix();
					gl.glScalef(.15f, .15f, 1f);

					if (EnemyManager.getInstance().getEnemyList().get(x).posY >= 3) {
						EnemyManager.getInstance().getEnemyList().get(x).posY -= Engine.WARSHIP_SPEED;

					} else {
						if (!EnemyManager.getInstance().getEnemyList().get(x).isLockedOn) {
							EnemyManager.getInstance().getEnemyList().get(x).lockOnPosX = randomPos
									.nextFloat() * 3;
							EnemyManager.getInstance().getEnemyList().get(x).isLockedOn = true;
							EnemyManager.getInstance().getEnemyList().get(x).incrementXToTarget = (float) ((EnemyManager
									.getInstance().getEnemyList().get(x).lockOnPosX - EnemyManager
									.getInstance().getEnemyList().get(x).posX) / (EnemyManager
											.getInstance().getEnemyList().get(x).posY / (Engine.WARSHIP_SPEED * 4)));
						}
						EnemyManager.getInstance().getEnemyList().get(x).posY -= (Engine.WARSHIP_SPEED * 2);
						EnemyManager.getInstance().getEnemyList().get(x).posX += EnemyManager
								.getInstance().getEnemyList().get(x).incrementXToTarget;

					}
					gl.glTranslatef(EnemyManager.getInstance().getEnemyList()
							.get(x).posX, EnemyManager.getInstance()
							.getEnemyList().get(x).posY, 0f);
					gl.glMatrixMode(GL10.GL_TEXTURE);
					gl.glLoadIdentity();
					gl.glTranslatef(0.75f, .25f, 0.0f);
					EnemyManager.getInstance().getEnemyList().get(x)
					.draw(gl, spriteSheets);
					gl.glPopMatrix();
					gl.glLoadIdentity();

					break;

				}

			}
		}

	}

	/**
	 * Method to move
	 * 
	 * @param gl
	 */
	private void movePlayer1(GL10 gl) {
		if (!player1.isDestroyed()) {
			switch (Engine.playerFlightAction) {
			
			case Engine.PLAYER_BANK_LEFT_1: // Going LEFT
				gl.glMatrixMode(GL10.GL_MODELVIEW);
				gl.glLoadIdentity();
				gl.glPushMatrix();
				gl.glScalef(.15f, .15f, 1f);
				
				//if (goodGuyBankFrames < Engine.PLAYER_FRAMES_BETWEEN_ANI && Engine.playerBankPosX > 0) {
				if (Engine.playerBankPosX > 0) {
					Engine.playerBankPosX -= Engine.PLAYER_BANK_SPEED;
					gl.glTranslatef(Engine.playerBankPosX, 0f, 0f);
					gl.glMatrixMode(GL10.GL_TEXTURE);
					gl.glLoadIdentity();
					gl.glTranslatef(0.0f, 0.34f, 0.0f);
					//goodGuyBankFrames += 1;
				} /*else if (goodGuyBankFrames >= Engine.PLAYER_FRAMES_BETWEEN_ANI
						&& Engine.playerBankPosX > 0) {
					Engine.playerBankPosX -= Engine.PLAYER_BANK_SPEED;
					gl.glTranslatef(Engine.playerBankPosX, 0f, 0f);
					gl.glMatrixMode(GL10.GL_TEXTURE);
					gl.glLoadIdentity();
					//gl.glTranslatef(0.0f, 0.25f, 0.0f);
					gl.glTranslatef(0.0f, 0.0f, 0.0f);
				} */else {
					gl.glTranslatef(Engine.playerBankPosX, 0f, 0f);
					gl.glMatrixMode(GL10.GL_TEXTURE);
					gl.glLoadIdentity();
					//gl.glTranslatef(0.0f, 0.0f, 0.0f);
					gl.glTranslatef(0.33f, 0.0f, 0.0f);
				}
				
				player1.draw(gl, spriteSheets);
				gl.glPopMatrix();
				gl.glLoadIdentity();
				break;
				
			case Engine.PLAYER_BANK_RIGHT_1: // Going RIGHT
				gl.glMatrixMode(GL10.GL_MODELVIEW);
				gl.glLoadIdentity();
				gl.glPushMatrix();
				gl.glScalef(.15f, .15f, 1f);
				//if (goodGuyBankFrames < Engine.PLAYER_FRAMES_BETWEEN_ANI && Engine.playerBankPosX < 3) {
				if (Engine.playerBankPosX < 5.5f) {
					Engine.playerBankPosX += Engine.PLAYER_BANK_SPEED;
					gl.glTranslatef(Engine.playerBankPosX, 0f, 0f);
					gl.glMatrixMode(GL10.GL_TEXTURE);
					gl.glLoadIdentity();
					//gl.glTranslatef(0.25f, 0.0f, 0.0f);
					gl.glTranslatef(0.66f, 0.34f, 0.0f);
					//goodGuyBankFrames += 1;
				} /*else if (goodGuyBankFrames >= Engine.PLAYER_FRAMES_BETWEEN_ANI && Engine.playerBankPosX < 3) {
					gl.glTranslatef(Engine.playerBankPosX, 0f, 0f);
					gl.glMatrixMode(GL10.GL_TEXTURE);
					gl.glLoadIdentity();
					gl.glTranslatef(0.66f, 0.0f, 0.0f);
					System.out.println("2");
					//gl.glTranslatef(0.50f, 0.0f, 0.0f);
					Engine.playerBankPosX += Engine.PLAYER_BANK_SPEED;
				} */else {
					gl.glTranslatef(Engine.playerBankPosX, 0f, 0f);
					gl.glMatrixMode(GL10.GL_TEXTURE);
					gl.glLoadIdentity();
					gl.glTranslatef(0.33f, 0.0f, 0.0f);
					//gl.glTranslatef(0.0f, 0.0f, 0.0f);
				}
				player1.draw(gl, spriteSheets);
				// Recover previous Matrix
				gl.glPopMatrix();
				gl.glLoadIdentity();
				break;
				
			case Engine.PLAYER_RELEASE: // Stay
				gl.glMatrixMode(GL10.GL_MODELVIEW);
				// Save Matrix before conversions
				gl.glLoadIdentity();
				gl.glPushMatrix();
				// Transformations to display the player
				gl.glScalef(.15f, .15f, 1f);
				gl.glTranslatef(Engine.playerBankPosX, 0f, 0f);
				gl.glMatrixMode(GL10.GL_TEXTURE);
				gl.glLoadIdentity();
				//gl.glTranslatef(0.0f, 0.0f, 0.0f);
				gl.glTranslatef(0.33f, 0.0f, 0.0f);
				//goodGuyBankFrames = 0;
				player1.draw(gl, spriteSheets);
				// Recover previous Matrix
				gl.glPopMatrix();
				gl.glLoadIdentity();
				break;
				
			default:
				gl.glMatrixMode(GL10.GL_MODELVIEW);
				// Save Matrix before conversions
				gl.glLoadIdentity();
				gl.glPushMatrix();
				// Transformations to display the player
				gl.glScalef(.15f, .15f, 1f);
				gl.glTranslatef(Engine.playerBankPosX, 0f, 0f);
				gl.glMatrixMode(GL10.GL_TEXTURE);
				gl.glLoadIdentity();
				//gl.glTranslatef(0.0f, 0.0f, 0.0f);
				gl.glTranslatef(0.33f, 0.0f, 0.0f);
				player1.draw(gl, spriteSheets);
				// Recover previous Matrix
				gl.glPopMatrix();
				gl.glLoadIdentity();
				break;
			}
			
			WeaponManager.getInstance().firePlayerWeapon(gl, spriteSheets);
		}

	}

	/**
	 * 
	 */
	private void detectCollisions() {
		for (int y = 0; y < 3; y++) {
			if (WeaponManager.getInstance().getPlayeFireList().get(y).shotFired) {
				for (int x = 0; x < Engine.TOTAL_INTERCEPTORS
						+ Engine.TOTAL_SCOUTS + Engine.TOTAL_WARSHIPS - 1; x++) {
					if (!EnemyManager.getInstance().getEnemyList().get(x).isDestroyed
							&& EnemyManager.getInstance().getEnemyList().get(x).posY < 4.25) {
						if ((WeaponManager.getInstance().getPlayeFireList()
								.get(y).posY >= EnemyManager.getInstance()
								.getEnemyList().get(x).posY - 1 && WeaponManager
								.getInstance().getPlayeFireList().get(y).posY <= EnemyManager
								.getInstance().getEnemyList().get(x).posY)
								&& (WeaponManager.getInstance()
										.getPlayeFireList().get(y).posX <= EnemyManager
										.getInstance().getEnemyList().get(x).posX + 1 && WeaponManager
										.getInstance().getPlayeFireList()
										.get(y).posX >= EnemyManager
										.getInstance().getEnemyList().get(x).posX - 1)) {
							int nextShot = 0;
							EnemyManager.getInstance().getEnemyList().get(x)
							.applyDamage();
							WeaponManager.getInstance().getPlayeFireList()
							.get(y).shotFired = false;
							if (y == 3) {
								nextShot = 0;
							} else {
								nextShot = y + 1;
							}
							if (WeaponManager.getInstance().getPlayeFireList()
									.get(nextShot).shotFired == false) {
								WeaponManager.getInstance().getPlayeFireList()
								.get(nextShot).shotFired = true;
								WeaponManager.getInstance().getPlayeFireList()
								.get(nextShot).posX = Engine.playerBankPosX;
								WeaponManager.getInstance().getPlayeFireList()
								.get(nextShot).posY = 1.25f;
							}
						}
					}
				}
			}
		}
	}

	

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// TODO Auto-generated method stub

		gl.glViewport(0, 0, width, height);

		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();

		gl.glOrthof(0f, 1f, 0f, 1f, -1f, 1f);

	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// TODO Auto-generated method stub
		initializeInterceptors();
		initializeScouts();
		initializeWarships();
		WeaponManager.getInstance().initializePlayerWeapon();
		textureLoader = new Texture(gl);
		spriteSheets = textureLoader.loadTexture(gl, Engine.CHARACTER_SHEET,Engine.context, 1);
		spriteSheets = textureLoader.loadTexture(gl, Engine.WEAPONS_SHEET,Engine.context, 2);
		spriteSheets = textureLoader.loadTexture(gl, Engine.PLAYER_SHEET,Engine.context, 3);

		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glClearDepthf(1.0f);
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glDepthFunc(GL10.GL_LEQUAL);

		BackGroundManager.getInstance().loadTextures(gl);
		// background.loadTexture(gl,Engine.BACKGROUND_LAYER_ONE,
		// Engine.context);
		// background2.loadTexture(gl,Engine.BACKGROUND_LAYER_TWO,
		// Engine.context);
	}

}
