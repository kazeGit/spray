/*
 * Copyright (C) 2011-2013 spray.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package spray.routing
package directives

import shapeless.HNil

trait SchemeDirectives {
  import BasicDirectives._
  import MiscDirectives._
  import RouteDirectives._

  /**
   * Extracts the Uri scheme from the request.
   */
  def schemeName: Directive1[String] = extract(_.request.uri.scheme)

  /**
   * Rejects all requests whose Uri scheme does not match the given one.
   */
  def scheme(schm: String): Directive0 =
    schemeName.require(_ == schm, SchemeRejection(schm)) &
      cancelAllRejections(ofType[SchemeRejection])
}

object SchemeDirectives extends SchemeDirectives
