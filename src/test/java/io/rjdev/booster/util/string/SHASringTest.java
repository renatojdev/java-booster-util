package io.rjdev.booster.util.string;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.rjdev.booster.util.string.SHAString.Type;

public class SHASringTest {

    @BeforeAll
    public static void start() {
        System.out.println("=======Starting junit 5 tests========");
    }

    @Test
    public void test_hash_256_512(){
        assert("4d114a5f27e42f0a8fe269c925f53fb72682e46848c65da8c0b43e1ea2e655a2".equals(SHAString.hash("athletico", Type.SHA_256)));
        assert("437e7c5ce268e0bb2834e4b2ec0328fa48ce7da7837ca19de37ad4b96b2fe6b6724f9512257e18f97d5fa226613ac773f246a663e700954e99a8330ce4456c2b".equals(SHAString.hash("athletico", Type.SHA_512)));
    }

}
