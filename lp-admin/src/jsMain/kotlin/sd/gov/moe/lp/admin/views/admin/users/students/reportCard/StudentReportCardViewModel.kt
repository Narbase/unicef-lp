package sd.gov.moe.lp.admin.views.admin.users.students.reportCard

import com.narbase.kunafa.core.lifecycle.Observable
import sd.gov.moe.lp.admin.network.basicNetworkCall
import sd.gov.moe.lp.admin.network.remoteProcess
import sd.gov.moe.lp.admin.utils.BasicUiState
import sd.gov.moe.lp.dto.common.StringUUID
import sd.gov.moe.lp.dto.domain.admin.GetStudentReportCardEndpoint
import sd.gov.moe.lp.dto.models.ExtendedStudentReportCardDto

class StudentReportCardViewModel {
    var studentId: StringUUID? = null
    val uiState = Observable<BasicUiState>()
    val downLoadUiState = Observable<BasicUiState>()
    var reportCard: ExtendedStudentReportCardDto? = null

    fun setStudentId(id: StringUUID) {
        studentId = id
    }

    fun getReportCard() {
        val id = studentId ?: return
        basicNetworkCall(uiState) {
            val response = GetStudentReportCardEndpoint.remoteProcess(GetStudentReportCardEndpoint.Request(id))
            reportCard = response.data.extendedStudentReportCard
        }
    }

    fun downloadReportCard() {
        val id = studentId ?: return
        basicNetworkCall(downLoadUiState) {

        }
    }
}