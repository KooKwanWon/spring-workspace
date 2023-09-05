package com.kh.character;

public class Sword extends Weapon {


	public Sword() {}
	
	public Sword(String name) {
		super(name);
		
	}
	
	@Override
	public String attack() throws Exception {
		return "칼을 휘두른다.";
	}
	
}
