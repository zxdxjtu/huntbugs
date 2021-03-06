/*
 * Copyright 2016 HuntBugs contributors
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package one.util.huntbugs.testdata;

import java.util.List;

import org.junit.Assert;

import one.util.huntbugs.registry.anno.AssertNoWarning;
import one.util.huntbugs.registry.anno.AssertWarning;

/**
 * @author Tagir Valeev
 *
 */
public class TestSelfComputation {
    @AssertWarning("SelfComputation")
    public int test(int a, int b) {
        return (a - b) / (a - b);
    }

    @AssertWarning("SelfComputation")
    public int test(int[] x) {
        return x[1] - x[1];
    }

    @AssertWarning("SelfComparison")
    public boolean testCmp(int[] x) {
        return x[1] == x[1];
    }
    
    @AssertWarning("SelfComparison")
    public boolean testCmp(double[] x, double[] y) {    
        return x[1] + y[0] >= x[1] + y[0];
    }
    
    @AssertWarning("SelfEquals")
    public boolean testEquals(String s1) {    
        return s1.equals(s1);
    }
    
    @AssertNoWarning("SelfEquals")
    public void testMyEquals(Object obj) {
        Assert.assertTrue(obj.equals(obj));
    }
    
    @AssertNoWarning("SelfComputation")
    public int test(int[] x, int idx) {
        return x[idx++] - x[idx++];
    }
    
    @AssertNoWarning("*")
    public void testLambdas(List<Integer> l1, List<Integer> l2) {
        l1.forEach(a -> {
            l2.forEach(b -> {
                System.out.println(a - b);
            });
        });
    }
    

    // Fails due to Procyon bug, reported https://bitbucket.org/mstrobel/procyon/issues/287/variables-incorerctly-merged
//    @AssertNoWarning("SelfComputation")
//    public int appendDigits(long num, int maxdigits) {
//        char[] buf = new char[maxdigits];
//        int ix = maxdigits;
//        while (ix > 0) {
//            buf[--ix] = '0';
//        }
//        return maxdigits - ix;
//    }
}
