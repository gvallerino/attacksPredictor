package ar.com.fiuba.modelosIII.attacksPredictor.model;

import java.util.List;

import ar.com.fiuba.modelosIII.attacksPredictor.enums.AttackTypeEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.enums.RegionEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.enums.TargetTypeEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.enums.WeaponTypeEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.others.Constants;

public class TerroristAttack {

	private String id;
	private Integer year;
	private RegionEnum region;
	private Boolean multiple;
	private Boolean success;
	private Boolean suicide;
	private AttackTypeEnum attackType;
	private TargetTypeEnum targetType;
	private WeaponTypeEnum weaponType;
	private Integer amountKill;
	private Integer amountWound;
	private int[] gen = new int[Constants.COUNT_DATA_TYPE];
	private List<Integer> values;
	private int fitness;
	
	
	//me quede en que tengo que elegir que, para cada atributo, que es lo mejor para el hijo. Por ejemplo, el año pondria un random. no me importa
	//para los enums tengo que agregarle prioridad. para la cantidad de heridos y muertos elegiria el mayor (es mejor ataque).
	//todas estas decisiones tengo que tomarlas
	public TerroristAttack() {
		
	}
	
	public TerroristAttack (String id, List<Integer> values) {
		this.id = id;
		for (int i = 0; i < values.size(); i++) {
			int value = values.get(i).intValue();
			this.processGen(value,i);
			switch (i) {
				case 0: this.year = new Integer(value); break;
				case 1: this.region = RegionEnum.getById(value); break;
				case 2: this.multiple = new Boolean(value == 1);break;
				case 3: this.success = new Boolean(value == 1);break;
				case 4: this.suicide = new Boolean(value == 1);break;
				case 5: this.attackType = AttackTypeEnum.getById(value); break;
				case 6: this.targetType = TargetTypeEnum.getById(value); break;
				case 7: this.weaponType = WeaponTypeEnum.getById(value); break;
				case 8: this.amountKill = new Integer(value); break;
				case 9: this.amountWound = new Integer(value); break;
			}
		}
		this.values = values;
	}
	
	private void processGen(int value, int i) {
		int genValue = value % 2;
		fitness += genValue;
		gen[i] = genValue;
	}
	
	public boolean equals(TerroristAttack other) {
		return this.id.equalsIgnoreCase(other.getId());
	}
	
	public boolean match(TerroristAttack other) {
		
		boolean id = other.getId() == null || this.id.equalsIgnoreCase(other.getId());
		boolean year = other.getYear() == null || this.year.equals(other.getYear());
		boolean region = other.getRegion() == null || this.region.equals(other.getRegion());
		boolean multiple = other.isMultiple() == null || this.multiple.equals(other.isMultiple());
		boolean success = other.isSuccess() == null || this.success.equals(other.isSuccess());
		boolean suicide = other.isSuicide() == null || this.suicide.equals(other.isSuicide());
		boolean attackType = other.getAttackType() == null || this.attackType.equals(other.getAttackType());
		boolean targetType = other.getTargetType() == null || this.targetType.equals(other.getTargetType());
		boolean weaponType = other.getWeaponType() == null || this.weaponType.equals(other.getWeaponType());
		boolean amountKill = other.getAmountKill() == null || this.amountKill.equals(other.getAmountKill());
		boolean amountWound = other.getAmountWound() == null || this.amountWound.equals(other.getAmountWound());
		return id && year && region && multiple && success && suicide && attackType && targetType && weaponType && amountKill && amountWound;
	}
  
	public void print() {
		System.out.print("TA: " + this.getId() + " -> ");
		System.out.print("[ ");
		for (Integer value : values) {
			System.out.print(value + " ");
		}
		System.out.println("]");
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public RegionEnum getRegion() {
		return region;
	}

	public void setRegion(RegionEnum region) {
		this.region = region;
	}

	public Boolean isMultiple() {
		return multiple;
	}

	public void setMultiple(Boolean multiple) {
		this.multiple = multiple;
	}

	public Boolean isSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public Boolean isSuicide() {
		return suicide;
	}

	public void setSuicide(Boolean suicide) {
		this.suicide = suicide;
	}

	public AttackTypeEnum getAttackType() {
		return attackType;
	}

	public void setAttackType(AttackTypeEnum attackType) {
		this.attackType = attackType;
	}

	public TargetTypeEnum getTargetType() {
		return targetType;
	}

	public void setTargetType(TargetTypeEnum targetType) {
		this.targetType = targetType;
	}

	public WeaponTypeEnum getWeaponType() {
		return weaponType;
	}

	public void setWeaponType(WeaponTypeEnum weaponType) {
		this.weaponType = weaponType;
	}

	public Integer getAmountKill() {
		return amountKill;
	}

	public void setAmountKill(Integer amountKill) {
		this.amountKill = amountKill;
	}

	public Integer getAmountWound() {
		return amountWound;
	}

	public void setAmountWound(Integer amountWound) {
		this.amountWound = amountWound;
	}
	
	public int getFitness() {
		return this.fitness;
	}
	
	public List<Integer> getValues() {
		return this.values;
	}
	
}
