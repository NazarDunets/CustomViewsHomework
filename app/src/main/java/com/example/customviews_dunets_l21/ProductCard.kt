package com.example.customviews_dunets_l21

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.LinearLayout
import com.example.customviews_dunets_l21.databinding.ProductCardBinding

class ProductCard(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private val binding: ProductCardBinding =
        ProductCardBinding.bind(inflate(context, R.layout.product_card, this))

    var drawable: Drawable?
        get() = binding.ivPreview.drawable
        set(value) {
            value?.let { binding.ivPreview.setImageDrawable(it) }
        }

    var title: String
        get() = binding.tvTitle.text.toString()
        set(value) {
            binding.tvTitle.text = value
        }

    var category: String
        get() = binding.tvCategory.text.toString()
        set(value) {
            binding.tvCategory.text = value
        }

    var rating: Float
        get() = binding.rbRating.rating
        set(value) {
            binding.rbRating.rating = value
        }


    init {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.ProductCard)

        binding.apply {
            ivPreview.setImageResource(
                attributes.getResourceId(
                    R.styleable.ProductCard_android_src,
                    R.color.purple_200
                )
            )
            tvTitle.text =
                attributes.getText(R.styleable.ProductCard_android_title)
            tvCategory.text =
                attributes.getText(R.styleable.ProductCard_android_category)
            rbRating.rating =
                attributes.getFloat(R.styleable.ProductCard_android_rating, 0f)

            val currency =
                attributes.getString(R.styleable.ProductCard_currency)
            val priceFormatted =
                "%.2f".format(attributes.getFloat(R.styleable.ProductCard_price, 0f))

            tvPrice.text = context.getString(R.string.price_arrangment, currency, priceFormatted)
        }
        attributes.recycle()
    }

}
