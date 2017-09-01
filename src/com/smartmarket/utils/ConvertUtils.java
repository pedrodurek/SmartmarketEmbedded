package com.smartmarket.utils;

import java.util.BitSet;

public class ConvertUtils {
	
	public static BitSet fromByte(byte b) { //Convert a byte into Bitset
		
        BitSet bits = new BitSet(8);

        for (int i = 0; i < 8; i++) {
            bits.set(i, (b & 1) == 1);
            b >>= 1;
        }

        return bits;
        
    }

}
