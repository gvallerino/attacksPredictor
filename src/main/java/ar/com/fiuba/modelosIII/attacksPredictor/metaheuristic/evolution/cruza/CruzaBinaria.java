package ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.evolution.cruza;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

import org.apache.commons.codec.BinaryDecoder;
import org.apache.commons.codec.binary.BinaryCodec;
import org.apache.poi.hssf.record.FilePassRecord.XorKeyData;

import ar.com.fiuba.modelosIII.attacksPredictor.enums.model.AttackTypeEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.enums.model.RegionEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.enums.model.TargetTypeEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.enums.model.WeaponTypeEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.evolution.Reproduction;
import ar.com.fiuba.modelosIII.attacksPredictor.model.TerroristAttack;
import ar.com.fiuba.modelosIII.attacksPredictor.others.Constants;

public class CruzaBinaria extends Reproduction implements Cruzable {
	
	private static CruzaBinaria INSTANCE = null;

	private CruzaBinaria() {}
	
	public static CruzaBinaria getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new CruzaBinaria();
		}
		return INSTANCE;
	}
	
//	@Override
//	public TerroristAttack cruzar(TerroristAttack father, TerroristAttack mother) {
//		BitSet bitsFather = father.getBits();
//		BitSet bitsMother = mother.getBits();
//		BitSet bitsSon = (BitSet) bitsFather.clone();
//		bitsSon.xor(bitsMother);
//		TerroristAttack son = this.cruzar(father,mother,bitsSon);
//		if (son.getYear().equals(0)) son.setYear(father.getYear());
//		if (son.getRegion().equals(RegionEnum.OTHER)) son.setRegion(father.getRegion());
//		if (son.getAttackType().equals(AttackTypeEnum.OTHER)) son.setAttackType(father.getAttackType());
//		if (son.getTargetType().equals(TargetTypeEnum.NOTHING)) son.setTargetType(father.getTargetType());
//		if (son.getWeaponType().equals(WeaponTypeEnum.NOTHING)) son.setWeaponType(father.getWeaponType());
//		return son;
//	}
	
	@Override
	public TerroristAttack cruzar(TerroristAttack father, TerroristAttack mother) {
		List<Integer> sonValue = new ArrayList<Integer>();
		int voteFather = 0;
		
		boolean isMultiple = Boolean.logicalXor(father.isMultiple(), mother.isMultiple());
		if (isMultiple) {
			voteFather += father.isMultiple() ? 1 : 0;
		}
		
		boolean isSuccess = Boolean.logicalXor(father.isSuccess(), mother.isSuccess());
		if (isSuccess) {
			voteFather += father.isSuccess() ? 1 : 0;
		}
		
		boolean isSuicide = Boolean.logicalXor(father.isSuicide(), mother.isSuicide());
		if (isSuicide) {
			voteFather += father.isSuicide() ? 1 : 0;
		}
		
		double probability = (voteFather / 3.0d) * 100;
		if (probability == 0) probability = 50;
		Integer year = Constants.choice(father.getYear(), mother.getYear(), probability);
		Integer regionId = Constants.choice(father.getRegion().id, mother.getRegion().id, probability);
		Integer attackId = Constants.choice(father.getAttackType().id, mother.getAttackType().id, probability);
		Integer targetId = Constants.choice(father.getTargetType().id, mother.getTargetType().id, probability);
		Integer weaponId = Constants.choice(father.getWeaponType().id, mother.getWeaponType().id, probability);
		Integer amountKills = xorNumbers(father.getAmountKill(), mother.getAmountKill());
		Integer amountWounds = xorNumbers(father.getAmountWound(), mother.getAmountWound());
		
		sonValue.add(year);
		sonValue.add(regionId);
		sonValue.add(isMultiple ? 1 : 0);
		sonValue.add(isSuccess ? 1 : 0);
		sonValue.add(isSuicide ? 1 : 0);
		sonValue.add(attackId);
		sonValue.add(targetId);
		sonValue.add(weaponId);
		sonValue.add(amountKills);
		sonValue.add(amountWounds);
			
		TerroristAttack son = this.cruzar(father,mother,sonValue);
		return son;
	}
	
	
	private Integer xorNumbers(Integer value1, Integer value2) {
		try {
			
			if (value1.intValue() == 0 && value2.intValue() == 0) return 1;
			String value1Str = Integer.toBinaryString(value1);
			String value2Str = Integer.toBinaryString(value2);
			BitSet value1BS = this.fromString(value1Str);
			BitSet value2BS = this.fromString(value2Str);
			value1BS.xor(value2BS);
			if (value1BS.length() == 0) return 0;
			
			StringBuilder binary = new StringBuilder("");
			for (int i = 0; i < value1BS.length(); i++) {
				binary.append(value1BS.get(i) ? "1" : "0");
			}
			Integer value = Integer.parseInt(binary.reverse().toString(), 2);
			return value;
		} catch (Exception e) {
			return 0;
		}
	}
	
	private BitSet fromString(String binary) {
        return BitSet.valueOf(new long[] { Long.parseLong(binary, 2) });
}

}
