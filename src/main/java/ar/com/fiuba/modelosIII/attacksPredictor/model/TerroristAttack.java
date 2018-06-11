package ar.com.fiuba.modelosIII.attacksPredictor.model;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

import ar.com.fiuba.modelosIII.attacksPredictor.enums.model.AttackTypeEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.enums.model.RegionEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.enums.model.TargetTypeEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.enums.model.WeaponTypeEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.others.Constants;

public class TerroristAttack {

	protected String id;
	
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
	
	protected List<Integer> values;
	private BitSet valuesBinary;
	
	private List<Integer> fitnessValues;
	private int fitness;
	
	//me quede en que tengo que elegir que, para cada atributo, que es lo mejor para el hijo. Por ejemplo, el año pondria un random. no me importa
	//para los enums tengo que agregarle prioridad. para la cantidad de heridos y muertos elegiria el mayor (es mejor ataque).
	//todas estas decisiones tengo que tomarlas
	public TerroristAttack() {
		this.values = new ArrayList<Integer>();
		this.valuesBinary = new BitSet(Constants.COUNT_DATA_TYPE_BINARY());
		this.fitnessValues = new ArrayList<Integer>();
	}
	
	public TerroristAttack (String id, BitSet bits) {
		this.id = id;
		this.valuesBinary = bits;
		this.values = DecodeBinary.decode(bits);
		this.buildTerroristAttackByValues();
		this.processFitness();
	}
	
	public TerroristAttack (String id, List<Integer> values) {
		this.id = id;
		this.values = values;
		this.buildTerroristAttackByValues();
		this.valuesBinary = CodeBinary.code(values);
		this.processFitness();
	}
	
	private void buildTerroristAttackByValues() {
		for (int i = 0; i < values.size(); i++) {
			int value = values.get(i).intValue();
			switch (i) {
				case 0: this.year = new Integer(value); break;
				case 1: this.region = RegionEnum.getById(value); break;
				case 2: this.multiple = new Boolean(value == 1); break;
				case 3: this.success = new Boolean(value == 1); break;
				case 4: this.suicide = new Boolean(value == 1); break;
				case 5: this.attackType = AttackTypeEnum.getById(value); break;
				case 6: this.targetType = TargetTypeEnum.getById(value); break;
				case 7: this.weaponType = WeaponTypeEnum.getById(value); break;
				case 8: this.amountKill = new Integer(value); break;
				case 9: this.amountWound = new Integer(value); break;
			}
		}
	}
	
	private void processFitness() {
		this.fitnessValues = FitnessCalculator.calculate(this.values);
		this.fitness = 0;
		for (Integer fit : fitnessValues) {
			fitness += fit;
		}
	}
	
	public void print() {
		System.out.print("TA: " + this.getId() + " -> ");
		System.out.print("[ ");
		for (Integer value : values) {
			System.out.print(value + " ");
		}
		System.out.println("]");
	}
	
	public void printBinary() {
		System.out.print("[");
		for (int i = 0; i < valuesBinary.size(); i++) {
			System.out.print(valuesBinary.get(i) + " ");
		}
		System.out.println("]");
	}
	
	
	
	public int getFitnessByProperty(int propertyPosition) {
		return this.fitnessValues.get(propertyPosition);
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
		CodeBinary.codeYear(this.valuesBinary, year);
	}

	public RegionEnum getRegion() {
		return region;
	}

	public void setRegion(RegionEnum region) {
		this.region = region;
		CodeBinary.codeByType(this.valuesBinary, region.id, 1);
	}

	public Boolean isMultiple() {
		return multiple;
	}

	public void setMultiple(Boolean multiple) {
		this.multiple = multiple;
		int value = multiple ? 1 : 0;
		CodeBinary.codeBoolean(this.valuesBinary, value, 2);
	}

	public Boolean isSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
		int value = success ? 1 : 0;
		CodeBinary.codeBoolean(this.valuesBinary, value, 3);
	}

	public Boolean isSuicide() {
		return suicide;
	}

	public void setSuicide(Boolean suicide) {
		this.suicide = suicide;
		int value = suicide ? 1 : 0;
		CodeBinary.codeBoolean(this.valuesBinary, value, 4);
	}

	public AttackTypeEnum getAttackType() {
		return attackType;
	}

	public void setAttackType(AttackTypeEnum attackType) {
		this.attackType = attackType;
		CodeBinary.codeByType(this.valuesBinary, attackType.id, 5);
	}

	public TargetTypeEnum getTargetType() {
		return targetType;
	}

	public void setTargetType(TargetTypeEnum targetType) {
		this.targetType = targetType;
		CodeBinary.codeByType(this.valuesBinary, targetType.id, 6);
	}

	public WeaponTypeEnum getWeaponType() {
		return weaponType;
	}

	public void setWeaponType(WeaponTypeEnum weaponType) {
		this.weaponType = weaponType;
		CodeBinary.codeByType(this.valuesBinary, weaponType.id, 7);
	}

	public Integer getAmountKill() {
		return amountKill;
	}

	public void setAmountKill(Integer amountKill) {
		this.amountKill = amountKill;
		CodeBinary.codeAmountKills(this.valuesBinary, amountKill);
	}

	public Integer getAmountWound() {
		return amountWound;
	}

	public void setAmountWound(Integer amountWound) {
		this.amountWound = amountWound;
		CodeBinary.codeAmountWound(this.valuesBinary, amountWound);
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
	
	public void setBits(BitSet bits) {
		this.valuesBinary = bits;
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
