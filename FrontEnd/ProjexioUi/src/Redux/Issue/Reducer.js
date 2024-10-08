import * as actionTypes from "./ActionTypes"
const initialState={
    issue:[],
    loading:false,
    error:null,
    issueDetails:null
};

const issueReducer=(state=initialState,action)=>{
    switch(action.type){
        case actionTypes.FETCH_ISSUES_REQUEST:
        case actionTypes.CREATE_ISSUE_REQUEST:
        case actionTypes.DELETE_ISSUE_REQUEST:
        case actionTypes.FETCH_ISSUES_BY_ID_REQUEST:
        case actionTypes.ASSIGNED_ISSUE_TO_USER_REQUEST:
            return{
                ...state,
                loading:true,
                error:null
            };

        case actionTypes.FETCH_ISSUES_SUCCESS:
        return{
            ...state,
            loading:false,
            issue:action.issues,
        };

        case actionTypes.FETCH_ISSUES_BY_ID_SUCCESS:
        case actionTypes.UPDATE_ISSUE_STATUS_SUCCESS:
        return{
            ...state,
            loading:false,
            issueDetails:action.issues
        };

        case actionTypes.CREATE_ISSUE_SUCCESS:
            return{
                ...state,
                loading:false,
                issue:[...state.issues,action.issue]
            };

        case actionTypes.ASSIGNED_ISSUE_TO_USER_SUCCESS:
            return{
                ...state,
                loading:false,
                issue:state.issue.map((issue)=>
                    issue.id===action.issue.id ? action.issue:issue
                ),
            };

        case actionTypes.DELETE_ISSUE_SUCCESS:
            return{
                ...state,
                loading:false,
                issue:state.issues.filter((issue)=>issue.id!== action.issueId),
                };

        case actionTypes.FETCH_ISSUES_FAILURE:
        case actionTypes.CREATE_ISSUE_FAILURE:
        case actionTypes.DELETE_ISSUE_FAILURE:
        case actionTypes.ASSIGNED_ISSUE_TO_USER_FAILURE:
            return{
                ...state,
                loading:false,
                error:action.error

            };

            default:
                return state;

    }
};
export default issueReducer;