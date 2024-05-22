package com.mthree.medicalsync.analytics

object AnalyticsEvents {

    const val NOTIFICATION_PERMISSION_DIALOG_SHOWN = "notification_permission_dialog_shown"
    const val NOTIFICATION_PERMISSION_DIALOG_DISMISSED = "notification_permission_dialog_shown"
    const val NOTIFICATION_PERMISSION_DIALOG_ALLOW_CLICKED = "notification_permission_dialog_allow_clicked"
    const val NOTIFICATION_PERMISSION_GRANTED = "notification_permission_granted"
    const val NOTIFICATION_PERMISSION_REFUSED = "notification_permission_refused"

    const val ALARM_PERMISSION_DIALOG_SHOWN = "alarm_permission_dialog_shown"
    const val ALARM_PERMISSION_DIALOG_DISMISSED = "alarm_permission_dialog_shown"
    const val ALARM_PERMISSION_DIALOG_ALLOW_CLICKED = "alarm_permission_dialog_allow_clicked"
    const val ALARM_PERMISSION_GRANTED = "alarm_permission_granted"
    const val ALARM_PERMISSION_REFUSED = "alarm_permission_refused"

    const val EMPTY_CARD_SHOWN = "empty_card_shown"

    const val ADD_MEDICATION_CLICKED_EMPTY_CARD = "add_medication_clicked_empty_card"
    const val ADD_MEDICATION_CLICKED_DAILY_OVERVIEW = "add_medication_clicked_daily_overview"
    const val ADD_MEDICATION_CLICKED_FAB = "add_medication_clicked_fab"

    const val ADD_CONTROL_CLICKED_EMPTY_CARD = "add_control_clicked_empty_card"
    const val ADD_CONTROL_CLICKED_DAILY_OVERVIEW = "add_control_clicked_daily_overview"
    const val ADD_CONTROL_CLICKED_FAB = "add_control_clicked_fab"

    const val TAKE_MEDICATION_CLICKED = "take_medication_clicked"

    const val MEDICATION_NOTIFICATION_SHOWN = "medication_notification_shown"
    const val MEDICATION_NOTIFICATION_SCHEDULED = "medication_notification_scheduled"

    const val CONTROL_NOTIFICATION_SHOWN = "control_notification_shown"
    const val CONTROL_NOTIFICATION_SCHEDULED = "control_notification_scheduled"

    const val MEDICATIONS_SAVED = "medications_saved"
    const val CONTROLS_SAVED = "controls_saved"

    const val MEDICATION_CONFIRM_ON_BACK_CLICKED = "medications_confirm_on_back_clicked"
    const val MEDICATION_CONFIRM_ON_CONFIRM_CLICKED = "medications_confirm_on_confirm_clicked"

    const val CONTROL_CONFIRM_ON_BACK_CLICKED = "controls_confirm_on_back_clicked"
    const val CONTROL_CONFIRM_ON_CONFIRM_CLICKED = "controls_confirm_on_confirm_clicked"

    const val ADD_MEDICATION_ON_BACK_CLICKED = "add_medication_on_back_clicked"
    const val ADD_MEDICATION_MEDICATION_VALUE_INVALIDATED = "add_medication_medication_%s_invalidated"
    const val ADD_MEDICATION_NAVIGATING_TO_MEDICATION_CONFIRM = "add_medication_navigating_to_medication_confirm"
    const val ADD_MEDICATION_ADD_TIME_CLICKED = "add_medication_add_time_clicked"
    const val ADD_MEDICATION_DELETE_TIME_CLICKED = "add_medication_delete_time_clicked"
    const val ADD_MEDICATION_NEW_TIME_SELECTED = "add_medication_new_time_selected"

    const val ADD_CONTROL_ON_BACK_CLICKED = "add_control_on_back_clicked"
    const val ADD_CONTROL_CONTROL_VALUE_INVALIDATED = "add_control_control_%s_invalidated"
    const val ADD_CONTROL_NAVIGATING_TO_CONTROL_CONFIRM = "add_control_navigating_to_control_confirm"
    const val ADD_CONTROL_ADD_TIME_CLICKED = "add_control_add_time_clicked"
    const val ADD_CONTROL_DELETE_TIME_CLICKED = "add_CONTROL_delete_time_clicked"
    const val ADD_CONTROL_NEW_TIME_SELECTED = "add_control_new_time_selected"

    const val REMINDER_NOTIFICATION_CLICKED = "reminder_notification_clicked"

    const val MEDICATION_DETAIL_ON_BACK_CLICKED = "medication_detail_on_back_clicked"
    const val MEDICATION_DETAIL_TAKEN_CLICKED = "medication_detail_taken_clicked"
    const val MEDICATION_DETAIL_SKIPPED_CLICKED = "medication_detail_skipped_clicked"
    const val MEDICATION_DETAIL_DONE_CLICKED = "medication_detail_done_clicked"

    const val CONTROL_DETAIL_ON_BACK_CLICKED = "control_detail_on_back_clicked"
    const val CONTROL_DETAIL_TAKEN_CLICKED = "control_detail_taken_clicked"
    const val CONTROL_DETAIL_SKIPPED_CLICKED = "control_detail_skipped_clicked"
    const val CONTROL_DETAIL_DONE_CLICKED = "control_detail_done_clicked"

    const val HOME_TAB_CLICKED = "home_tab_clicked"
    const val CONTROL_TAB_CLICKED = "control_tab_clicked"

    const val HOME_CALENDAR_PREVIOUS_WEEK_CLICKED = "home_calendar_previous_week_clicked"
    const val HOME_CALENDAR_NEXT_WEEK_CLICKED = "home_calendar_next_week_clicked"
    const val HOME_NEW_DATE_SELECTED = "home_new_date_clicked"

    const val CONTROL_CALENDAR_PREVIOUS_WEEK_CLICKED = "control_calendar_previous_week_clicked"
    const val CONTROL_CALENDAR_NEXT_WEEK_CLICKED = "control_calendar_next_week_clicked"
    const val CONTROL_NEW_DATE_SELECTED = "control_new_date_clicked"
}
