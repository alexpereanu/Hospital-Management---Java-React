import React from "react";
import Table from "../../commons/tables/table";


const columns = [
    {
        Header: 'ID',
        accessor: 'id',
    },
    {
        Header: 'DaysOfTreatment',
        accessor: 'daysOfTreatment',
    },
    {
        Header: 'IntakeIntervals',
        accessor: 'intakeIntervals',
    }
    ,
    {
        Header: 'Medical Condition',
        accessor: 'medicalCondition',
    },

];

const filters = [
    {
        accessor: 'LIST OF MEDICAL RECORDS',
    }
];

class MedicalRecordTable extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            tableData: this.props.tableData
        };
    }

    render() {
        return (
            <Table
                data={this.state.tableData}
                columns={columns}
                search={filters}
                pageSize={10}
            />
        )
    }
}

export default MedicalRecordTable;