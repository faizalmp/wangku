package id.faizalempe.data

import id.faizalempe.core.constant.RandomMatchers
import id.faizalempe.data.local.model.BalanceData
import id.faizalempe.data.local.model.TransactionBalanceData
import id.faizalempe.data.local.model.entity.TransactionEntity
import id.faizalempe.data.mapper.TransactionMapper
import id.faizalempe.domain.model.transaction.TransactionDto
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * @author Faizal Muhammad Priyowibowo (faizal.priyowibowo@dana.id)
 * @version TransactionMapperTest, v 0.1 19/12/22 18.13 by Faizal Muhammad Priyowibowo
 */
class TransactionMapperTest {

    @Test
    fun map_transaction_balance_data_to_dto_return_result() {
        // given
        val data = generateTransactionBalanceData()

        // when
        val result = TransactionMapper.mapTransactionBalanceDataToDto(data)

        // then
        assertEquals(data.balance.cashIn, result.balance.cashIn)
        assertEquals(data.transactions[0].amount, result.transactions[0].amount)
    }

    @Test
    fun map_transaction_balance_data_to_dto_return_default() {
        // given
        val data = TransactionBalanceData()

        // when
        val result = TransactionMapper.mapTransactionBalanceDataToDto(data)

        // then
        assertEquals(data.balance.cashIn, 0)
        assertEquals(data.transactions, emptyList<TransactionEntity>())
    }

    @Test
    fun map_transaction_balance_dto_to_data_return_result() {
        // given
        val data = generateTransactionDto()

        // when
        val result = TransactionMapper.mapTransactionDtoToEntity(data)

        // then
        assertEquals(data.desc, result.desc)
        assertEquals(data.category, result.category)
        assertEquals(data.amount, result.amount)
    }

    @Test
    fun map_transaction_balance_dto_to_data_return_default() {
        // given
        val data = TransactionDto()

        // when
        val result = TransactionMapper.mapTransactionDtoToEntity(data)

        // then
        assertEquals(data.desc, "")
        assertEquals(data.category, "")
        assertEquals(data.amount, 0)
    }

    private fun generateTransactionBalanceData(): TransactionBalanceData = TransactionBalanceData(
        balance = generateBalanceData(),
        transactions = listOf(generateTransactionEntity(), generateTransactionEntity())
    )

    private fun generateBalanceData(): BalanceData = BalanceData(
        total = RandomMatchers.getRandomLong(),
        cashIn = RandomMatchers.getRandomLong(),
        cashOut = RandomMatchers.getRandomLong()
    )

    private fun generateTransactionEntity(): TransactionEntity = TransactionEntity(
        id = RandomMatchers.getRandomInt(),
        category = RandomMatchers.getRandomString(),
        desc = RandomMatchers.getRandomString(),
        amount = RandomMatchers.getRandomLong(),
        datetime = RandomMatchers.getRandomString()
    )

    private fun generateTransactionDto(): TransactionDto = TransactionDto(
        id = RandomMatchers.getRandomInt(),
        category = RandomMatchers.getRandomString(),
        desc = RandomMatchers.getRandomString(),
        amount = RandomMatchers.getRandomLong(),
        datetime = RandomMatchers.getRandomString()
    )
}