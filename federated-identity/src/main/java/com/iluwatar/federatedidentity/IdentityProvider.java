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

public class IdentityProvider {
  private boolean isValid;
  private IdentityProviderDatabase identityProviderDatabase;

  IdentityProvider(IdentityProviderDatabase identityProviderDatabase) {
    this.identityProviderDatabase = identityProviderDatabase;
  }

  public void authenticate(Student student) {

    if (identityProviderDatabase.isValidUsername(student.getUsername()) && isValidPassword(student)) {
      setValid(true);
    } else {
      setValid(false);
    }
  }

  public boolean isValidPassword(Student student) {

    String passwordFromDb = identityProviderDatabase.getPasswordForUsername(student.getUsername());
    if (passwordFromDb.equals(student.getPassword())) {
      return true;
    } else {
      return false;
    }
  }

  public boolean isValid() {
    return isValid;
  }

  public void setValid(boolean valid) {
    isValid = valid;
  }
}
