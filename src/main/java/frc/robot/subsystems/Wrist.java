package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Wrist extends SubsystemBase {
	private final WPI_TalonFX WristMain = new WPI_TalonFX(Constants.Wrist.WristMain);
	private DigitalInput level_3;
	private DigitalInput level_4;
	int state = 1;
	double autospeed = 0;
	public boolean auto = false; // LOL

	public Wrist() {
		WristMain.configFactoryDefault();
		WristMain.setNeutralMode(NeutralMode.Brake);
		WristMain.setInverted(false);
		level_3 = new DigitalInput(3);
		level_4 = new DigitalInput(4);
		autospeed = 0;
		state = 1;
	}

	public int getState() {
		return state;
	}

	public double getAutoSpeed() {
		return autospeed;
	}

	public void wristStop() {
		WristMain.set(0);
		autospeed = 0;
	}

	public void wristUp(double POV) {
		// System.err.println("******************* level 3" + level_3.get()+
		// "\n********************** level 4"+level_4.get()+"POV " + POV + " autospeed "
		// + autospeed + " state " + state);
		if (auto == false) {
			double speed = 0;
			if (POV == -180) {
				speed = .75; // Down
			} else if (POV == 0) {
				speed = -0.75; // Up
			} else {
				speed = 0;
			}
			if (autospeed != 0) {
				speed = autospeed;
			}
			if (level_3.get() == false) {
				wristStop();
				state = 1; // Up
			}

			else if (level_4.get() == false) {
				wristStop();
				state = 2; // Down

			}
			// System.err.println("______________________________________SPEED" + speed
			// +"\n---------------------KILL ME-------");
			if (state != 1 && speed < 0) {
				WristMain.set(speed); // Going Up
				state = 4;

			} else if (state != 2 && speed > 0) {
				WristMain.set(speed); // Going Down
				state = 5;
			} else {
				WristMain.set(0);
			}
		} else {
			double speed = autospeed;
			if (level_3.get() == false && speed <= 0) {
				wristStop();
				state = 1; // Up
			}

			else if (level_4.get() == false && speed >= 0) {
				wristStop();
				state = 2; // Down

			}

			if (state != 1 && speed < 0) {
				WristMain.set(speed); // Going Up
				state = 4;

			}

			else if (state != 2 && speed > 0) {
				WristMain.set(speed); // Going Down
				state = 5;
			}

		}
	}

	public void wristTop() {
		autospeed = -.75;
	}

	public void wristDown() {
		autospeed = .75;
	}

}
