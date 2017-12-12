/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2017 Piyush Chaudhari
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.iluwatar.federatedidentity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class IdentityProviderTest {

  IdentityProvider identityProvider;
  @Mock
  IdentityProviderDatabase identityProviderDatabase;

  @Before
  public void setup(){
    identityProvider=new IdentityProvider(identityProviderDatabase);
    when(identityProviderDatabase.isValidUsername("piyush")).thenReturn(true);
    when(identityProviderDatabase.getPasswordForUsername("piyush")).thenReturn("abcd");
    when(identityProviderDatabase.isValidUsername("sam")).thenReturn(true);
    when(identityProviderDatabase.getPasswordForUsername("sam")).thenReturn("ab");
  }

  @Test
  public void authenticateTest(){
    Student student=new Student("piyush","abcd");

    boolean result=identityProvider.authenticate(student);

    Assert.assertEquals(true,result);
  }

  @Test
  public void shouldNotQueryToDatabase(){
    verifyNoMoreInteractions(identityProviderDatabase);
  }

  @Test
  public void shouldMatchCorrectPassword(){
    Student student1 = new Student("piyush", "abc");
    Student student2 = new Student("sam","ab");

    Assert.assertEquals(false,identityProvider.isValidPassword(student1));
    Assert.assertEquals(true,identityProvider.isValidPassword(student2));
  }
}