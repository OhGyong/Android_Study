package com.study.constraintset

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import androidx.constraintlayout.widget.ConstraintSet
import com.study.constraintset.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.btnTop.setOnClickListener {
            setConstraint("TOP")

        }

        mBinding.btnMiddle.setOnClickListener {
            setConstraint("MIDDLE")
        }

        mBinding.btnBottom.setOnClickListener {
            setConstraint("BOTTOM")
        }
    }

    /**
     * layout 이동 메서드
     */
    private fun setConstraint(location: String) {
        val constraints = ConstraintSet()
        constraints.clone(mBinding.clMain)
        constraints.removeFromVerticalChain(mBinding.clMove.id)
        constraints.removeFromHorizontalChain(mBinding.clMove.id)

        when(location) {
            "TOP" -> setConstraintConnect(constraints, mBinding.tvTop.id)
            "MIDDLE" -> setConstraintConnect(constraints, mBinding.tvMiddle.id)
            "BOTTOM" -> setConstraintConnect(constraints, mBinding.tvBottom.id)
        }

        constraints.applyTo(mBinding.clMain)
    }

    /**
     * 전달받은 targetId를 기준으로 clMove에 제약조건 적용 메서드
     */
    private fun setConstraintConnect(constraints: ConstraintSet, targetId: Int) {
        // clMove의 app:layout_constraintStart_toStartOf="targetId"와 같음
        constraints.connect(
            mBinding.clMove.id,
            ConstraintSet.START,
            targetId,
            ConstraintSet.START
        )

        // clMove의 app:layout_constraintTop_toBottomOf="targetId"와 같음
        constraints.connect(
            mBinding.clMove.id,
            ConstraintSet.TOP,
            targetId,
            ConstraintSet.BOTTOM
        )

        // marginTop 5 부여
        constraints.setMargin(
            mBinding.clMove.id,
            ConstraintSet.TOP,
            // dp 값 적용
            TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                5f,
                resources?.displayMetrics
            ).toInt()
        )
    }
}