package com.alexpetrov.applicationpicoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alexpetrov.applicationpicoroutines.databinding.ActivityMainBinding
import com.alexpetrov.applicationpicoroutines.fragments.FragmentA
import com.alexpetrov.applicationpicoroutines.fragments.FragmentB

/* Корутины — это блоки кода, которые работают асинхронно, то есть по очереди.
   В нужный момент исполнение такого блока приостанавливается
   с сохранением всех его свойств, чтобы запустился другой код.
   Когда управление возвращается к первому блоку, он продолжает работу.
   В результате программа выполняет несколько функций одновременно.

   Плюсы:
   Эффективность
   Снижение нагрузки на систему

   Минусы:
   Сложность и высокий порог вхождения
   Узкая специализация*/

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.containerA, FragmentA.newInstance())
            .commit()

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.containerB, FragmentB.newInstance())
            .commit()
    }
}