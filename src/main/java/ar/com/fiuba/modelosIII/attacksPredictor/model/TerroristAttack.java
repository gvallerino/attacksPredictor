package ar.com.fiuba.modelosIII.attacksPredictor.model;

import java.util.BitSet;
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
	
	private int[] fitnessValues = new int[Constants.COUNT_DATA_TYPE];
	private List<Integer> values;
	private int fitness;
	
	private BitSet valuesBinary = new BitSet(Constants.COUNT_BINARY_DATA_TYPE());
	private int[] firstPositionsBinary = Constants.FIRST_POSITION_BINARY(); 
	
	//me quede en que tengo que elegir que, para cada atributo, que es lo mejor para el hijo. Por ejemplo, el año pondria un random. no me importa
	//para los enums tengo que agregarle prioridad. para la cantidad de heridos y muertos elegiria el mayor (es mejor ataque).
	//todas estas decisiones tengo que tomarlas
	public TerroristAttack() {
		
	}
	
	//TODO: sacar el mapa de firstPositionsBinary
	public TerroristAttack (String id, List<Integer> values) {
		this.id = id;
		for (int i = 0; i < values.size(); i++) {
			int value = values.get(i).intValue();
			this.processFitness(value,i);
			switch (i) {
				case 0: this.year = new Integer(value); this.amountToBinary(value, i); break;
				case 1: this.region = RegionEnum.getById(value); this.typeToBinary(value, i); break;
				case 2: this.multiple = new Boolean(value == 1); this.booleanToBinary(value, i); break;
				case 3: this.success = new Boolean(value == 1); this.booleanToBinary(value, i); break;
				case 4: this.suicide = new Boolean(value == 1); this.booleanToBinary(value, i); break;
				case 5: this.attackType = AttackTypeEnum.getById(value); this.typeToBinary(value, i); break;
				case 6: this.targetType = TargetTypeEnum.getById(value); this.typeToBinary(value, i); break;
				case 7: this.weaponType = WeaponTypeEnum.getById(value); this.typeToBinary(value, i); break;
				case 8: this.amountKill = new Integer(value); this.amountToBinary(value, i); break;
				case 9: this.amountWound = new Integer(value); this.amountToBinary(value, i); break;
			}
		}
		this.values = values;
	}
	
	private void processFitness(int value, int i) {
		int fitnessValue = FitnessCalculator.calculate(value, i);
		fitness += fitnessValue;
		fitnessValues[i] = fitnessValue;
	}
	
	public void print() {
		System.out.print("TA: " + this.getId() + " -> ");
		System.out.print("[ ");
		for (Integer value : values) {
			System.out.print(value + " ");
		}
		System.out.println("]");
	}
	
	private void printBinary() {
		System.out.print("[");
		for (int i = 0; i < valuesBinary.size(); i++) {
			System.out.print(valuesBinary.get(i) + " ");
		}
		System.out.println("]");
	}
	
	private void amountToBinary(int amount, int caso) {
		int position = amount % Constants.COUNT_POSITION_BINARY[caso];
		valuesBinary.flip(position);
	}
	
	private void typeToBinary(int value, int caso) {
		this.flip(value, caso);
	}
	
	private void booleanToBinary(int value, int caso){
		if (value == 1) {
			flip(value, caso);
		}
	}
	
	private void flip(int value, int caso) {
		int initial = firstPositionsBinary[caso];
		int position = initial + value - 1;
		valuesBinary.flip(position);
	}
	
	public int getFitnessByProperty(int propertyPosition) {
		return this.fitnessValues[propertyPosition];
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
		this.amountToBinary(year, 0);
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
	
	public BitSet getBits() {
		return this.valuesBinary;
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
  
	
}
