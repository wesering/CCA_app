// $Id: package-info.java 231 2010-06-16 21:46:06Z tp $

/**
 * <p>The {@code com.USC.Cryptohash} package contains implementations of
 * various cryptographic hash functions.</p>
 *
 * <p>All implemented functions assume the format of a dedicated class,
 * with a no-argument constructor, and which implements the {@link
 * com.USC.Cryptohash.Digest Digest} interface. An instance of such a class
 * represents a stateful running computation, into which data is input,
 * and and the hash result is finally obtained.</p>
 *
 * <p>A hash function instance is not thread-safe; however, distinct
 * instances can be used concurrently with no ill effect. Instances
 * are independent of each other, and mobilize no special ressources
 * beyond a few plain Java objects. There is no need to "close" a
 * given instance in any way.</p>
 *
 * <p>An instance of {@link com.USC.Cryptohash.Digest Digest} can be
 * duplicated with the {@link com.USC.Cryptohash.Digest#copy copy()} method;
 * the returned clone is thereafter independent of the original. This
 * can be used to capture the hash function state at some point, after
 * some data bytes have been input.</p>
 *
 * <p>An instance of {@link com.USC.Cryptohash.Digest Digest} can be {@link
 * com.USC.Cryptohash.Digest#reset reset} at any time; this sets the hash
 * function back to its initial state, ready to accept a new message. A
 * call to {@link com.USC.Cryptohash.Digest#reset reset()} is automatically
 * implied when the current hash operation is terminated (with a {@code
 * digest()} method call).</p>
 *
 * <p>Apart from the hash function classes, the {@code com.USC.Cryptohash}
 * package contains the following:</p>
 * <ul>
 * <li>{@link com.USC.Cryptohash.Digest Digest}: the common interface.</li>
 * <li>{@link com.USC.Cryptohash.DigestEngine DigestEngine}: an abstract class
 * which helps with the implementation of {@link com.USC.Cryptohash.Digest
 * Digest}; most hash function implementations in this package use that
 * base class.</li>
 * <li>{@link com.USC.Cryptohash.HMAC HMAC}: an implementation of the HMAC
 * message authentication code (see
 * <a href="http://tools.ietf.org/html/rfc2104">RFC 2104</a>).
 * {@link com.USC.Cryptohash.HMAC HMAC} implements {@link com.USC.Cryptohash.Digest}
 * but is initialized with an underlying hash function implementation, and
 * a secret key.</li>
 * </ul>
 *
 * <pre>
 * ==========================(LICENSE BEGIN)============================
 *
 * Copyright (c) 2007-2010  Projet RNRT SAPHIR
 * 
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 * ===========================(LICENSE END)=============================
 * </pre>
 *
 * @version   $Revision: 231 $
 * @author    Thomas Pornin &lt;thomas.pornin@cryptolog.com&gt;
 */

package com.USC.Cryptohash;
