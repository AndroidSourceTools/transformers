package jp.wasabeef.transformations.glide

import android.content.Context
import android.graphics.Bitmap
import com.bumptech.glide.load.Key
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import jp.wasabeef.transformations.core.Crop
import jp.wasabeef.transformations.core.bitmapConfig
import java.security.MessageDigest

/**
 * Copyright (C) 2020 Wasabeef
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

class CropTransformation : BitmapTransformation {

  private var crop: Crop

  private var aspectRatio = 0f
  private var left = 0
  private var top = 0
  private var width = 0
  private var height = 0
  private var widthRatio = 0f
  private var heightRatio = 0f
  private var gravityHorizontal = Crop.GravityHorizontal.CENTER
  private var gravityVertical = Crop.GravityVertical.CENTER

  constructor(left: Int, top: Int, width: Int, height: Int) {
    crop = Crop(left, top, width, height)
    this.left = left
    this.top = top
    this.width = width
    this.height = height
  }

  @JvmOverloads
  constructor(
    width: Int,
    height: Int,
    gravityHorizontal: Crop.GravityHorizontal = Crop.GravityHorizontal.CENTER,
    gravityVertical: Crop.GravityVertical = Crop.GravityVertical.CENTER
  ) {
    crop = Crop(width, height, gravityHorizontal, gravityVertical)
    this.width = width
    this.height = height
    this.gravityHorizontal = gravityHorizontal
    this.gravityVertical = gravityVertical
  }

  @JvmOverloads
  constructor(
    widthRatio: Float,
    heightRatio: Float,
    gravityHorizontal: Crop.GravityHorizontal = Crop.GravityHorizontal.CENTER,
    gravityVertical: Crop.GravityVertical = Crop.GravityVertical.CENTER
  ) {
    crop = Crop(widthRatio, heightRatio, gravityHorizontal, gravityVertical)
    this.widthRatio = widthRatio
    this.heightRatio = heightRatio
    this.gravityHorizontal = gravityHorizontal
    this.gravityVertical = gravityVertical
  }

  constructor(
    width: Int, height: Int, aspectRatio: Float,
    gravityHorizontal: Crop.GravityHorizontal, gravityVertical: Crop.GravityVertical
  ) {
    crop = Crop(width, height, aspectRatio, gravityHorizontal, gravityVertical)
    this.width = width
    this.height = height
    this.aspectRatio = aspectRatio
    this.gravityHorizontal = gravityHorizontal
    this.gravityVertical = gravityVertical
  }

  constructor(
    widthRatio: Float, heightRatio: Float, aspectRatio: Float,
    gravityHorizontal: Crop.GravityHorizontal, gravityVertical: Crop.GravityVertical
  ) {
    crop = Crop(widthRatio, heightRatio, aspectRatio, gravityHorizontal, gravityVertical)
    this.widthRatio = widthRatio
    this.heightRatio = heightRatio
    this.aspectRatio = aspectRatio
    this.gravityHorizontal = gravityHorizontal
    this.gravityVertical = gravityVertical
  }

  constructor(
    aspectRatio: Float, gravityHorizontal: Crop.GravityHorizontal,
    gravityVertical: Crop.GravityVertical
  ) {
    crop = Crop(aspectRatio, gravityHorizontal, gravityVertical)
    this.aspectRatio = aspectRatio
    this.gravityHorizontal = gravityHorizontal
    this.gravityVertical = gravityVertical
  }

  override fun transform(
    context: Context,
    pool: BitmapPool,
    source: Bitmap,
    outWidth: Int,
    outHeight: Int
  ): Bitmap {
    val size = crop.calculateSize(source)
    val output = pool.get(size.width, size.height, bitmapConfig(source))
    return crop.transform(context, source, output)
  }

  override fun updateDiskCacheKey(messageDigest: MessageDigest) {
    messageDigest.update(crop.key().toByteArray(Key.CHARSET))
  }

  override fun equals(o: Any?): Boolean {
    if (this === o) return true
    if (javaClass != o?.javaClass) return false

    o as CropTransformation

    if (crop != o.crop) return false
    if (aspectRatio != o.aspectRatio) return false
    if (left != o.left) return false
    if (top != o.top) return false
    if (width != o.width) return false
    if (height != o.height) return false
    if (widthRatio != o.widthRatio) return false
    if (heightRatio != o.heightRatio) return false
    if (gravityHorizontal != o.gravityHorizontal) return false
    if (gravityVertical != o.gravityVertical) return false

    return true
  }

  override fun hashCode(): Int = crop.key().hashCode()
}