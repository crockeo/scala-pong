package org.crockeo.pong.objects

import org.crockeo.pong.State

trait Updateable {
  def update(dt: Float, s: State): Updateable
}