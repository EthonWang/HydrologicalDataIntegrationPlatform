/**
 * plugins/vuetify.js
 *
 * Framework documentation: https://vuetifyjs.com`
 */

// Styles
import '@mdi/font/css/materialdesignicons.css'
import 'vuetify/styles'
import { Icon } from '@iconify/vue'
import { aliases } from 'vuetify/lib/iconsets/mdi'

import {h} from "vue"

// Composables
import { createVuetify } from 'vuetify'
export const iconify = {
  component: props => h(Icon, props),
}
// https://vuetifyjs.com/en/introduction/why-vuetify/#feature-guides
export default createVuetify({
  theme: {
    themes: {
      light: {
        dark: false,
        colors: {
          'primary': '#696CFF',
          'secondary': '#03C3EC',
          'third': '#8352f1',
          'on-secondary': '#fff',
          'success': '#71DD37',
          'info': '#16B1FF',
          'warning': '#FFAB00',
          'error': '#ff3e1d',
          'on-primary': '#FFFFFF',
          'on-success': '#FFFFFF',
          'on-warning': '#FFFFFF',
          'background': '#F4F5FA',
          'on-background': '#3A3541',
          'on-surface': '#3A3541',
          'grey-50': '#FAFAFA',
          'grey-100': '#F5F5F5',
          'grey-200': '#EEEEEE',
          'grey-300': '#E0E0E0',
          'grey-400': '#BDBDBD',
          'grey-500': '#9E9E9E',
          'grey-600': '#757575',
          'grey-700': '#616161',
          'grey-800': '#424242',
          'grey-900': '#212121',
        },
        variables: {
          'border-color': '#3A3541',
          'medium-emphasis-opacity': 0.68,

          // Shadows
          'shadow-key-umbra-opacity': 'rgba(var(--v-theme-on-surface), 0.08)',
          'shadow-key-penumbra-opacity': 'rgba(var(--v-theme-on-surface), 0.12)',
          'shadow-key-ambient-opacity': 'rgba(var(--v-theme-on-surface), 0.04)',
        },
      },
    },
  },
  defaults:{
    VAlert: {
      density: 'comfortable',
    },
    VAvatar: {
      // ℹ️ Remove after next release
      variant: 'elevated',
    },
    VBadge: {
      // set v-badge default color to primary
      color: 'primary',
    },
    VBtn: {
      // set v-btn default color to primary
      color: 'primary',
    },
    VChip: {
      elevation: 0,
    },
    VMenu: {
      VList: {
        density: 'compact',
      },
    },
    VPagination: {
      activeColor: 'primary',
      density: 'comfortable',
    },
    VTabs: {
      // set v-tabs default color to primary
      color: 'primary',
    },
    VTooltip: {
      // set v-tooltip default location to top
      location: 'top',
    },

    // VList: {
    //   VListItem: {
    //     activeColor: 'primary',
    //   },
    // },
    VCheckbox: {
      // set v-checkbox default color to primary
      color: 'primary',
      density: 'comfortable',
      hideDetails: 'auto',
    },
    VRadioGroup: {
      color: 'primary',
      density: 'comfortable',
      hideDetails: 'auto',
    },
    VRadio: {
      density: 'comfortable',
      hideDetails: 'auto',
    },
    VSelect: {
      variant: 'outlined',
      density: 'comfortable',
      color: 'primary',
      hideDetails: 'auto',
    },
    VRangeSlider: {
      // set v-range-slider default color to primary
      color: 'primary',
      density: 'comfortable',
      thumbLabel: true,
      hideDetails: 'auto',
    },
    VRating: {
      // set v-rating default color to primary
      color: 'warning',
    },
    VProgressCircular: {
      // set v-progress-circular default color to primary
      color: 'primary',
    },
    VSlider: {
      // set v-slider default color to primary
      color: 'primary',
      hideDetails: 'auto',
    },
    VTextField: {
      variant: 'outlined',
      density: 'comfortable',
      color: 'primary',
      hideDetails: 'auto',
    },
    VAutocomplete: {
      variant: 'outlined',
      density: 'comfortable',
      color: 'primary',
      hideDetails: 'auto',
    },
    VCombobox: {
      variant: 'outlined',
      density: 'comfortable',
      color: 'primary',
      hideDetails: 'auto',
    },
    VFileInput: {
      variant: 'outlined',
      density: 'comfortable',
      color: 'primary',
      hideDetails: 'auto',
    },
    VTextarea: {
      variant: 'outlined',
      density: 'comfortable',
      color: 'primary',
      hideDetails: 'auto',
    },
    VSwitch: {
      // set v-switch default color to primary
      color: 'primary',
      hideDetails: 'auto',
    },
  },
  icons:{
    defaultSet: 'iconify',
    aliases,
    sets: {
      iconify,
    },
  }
})
