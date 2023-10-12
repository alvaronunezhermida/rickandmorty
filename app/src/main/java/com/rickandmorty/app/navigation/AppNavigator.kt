package com.rickandmorty.app.navigation

import javax.inject.Inject

/**
 * A navigation class that extends [ActivityNavigator] and provides navigation functions specific to the app's flow.
 *
 * This class defines navigation actions that allow transitioning between different fragments and screens within the application.
 *
 * @constructor Creates an [AppNavigator] instance using Dagger Hilt injection.
 * @see ActivityNavigator
 */
class AppNavigator @Inject constructor() : ActivityNavigator() {

}