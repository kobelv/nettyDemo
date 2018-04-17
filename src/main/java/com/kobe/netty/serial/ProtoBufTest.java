package com.kobe.netty.serial;

import com.google.protobuf.InvalidProtocolBufferException;
import com.kobe.netty.dto.AddressBookProtos;
import com.kobe.netty.dto.AddressBookProtos.Person;
import com.kobe.netty.dto.AddressBookProtos.Person.PhoneNumber;
import com.kobe.netty.dto.AddressBookProtos.Person.PhoneType;

public class ProtoBufTest {

	public static void main(String[] args) throws InvalidProtocolBufferException {
		PhoneNumber phone = PhoneNumber.newBuilder().setNumber("12345678").setType(PhoneType.MOBILE).build();
		Person kobe = Person.newBuilder().setEmail("xxx@gmail.com").setId(1).setName("kobe").addPhones(0, phone).build();		
		AddressBookProtos.AddressBook addr = AddressBookProtos.AddressBook.newBuilder().addPerson(kobe).build();
		
		byte[] bytes = addr.toByteArray();	
		
		AddressBookProtos.AddressBook newAddr = AddressBookProtos.AddressBook.parseFrom(bytes);
		System.out.println(newAddr.getPersonCount() + "--" + newAddr.getPerson(0).getName());
		
	}

}
